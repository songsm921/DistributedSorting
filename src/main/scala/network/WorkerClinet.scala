package network
import io.grpc.{Server, ServerBuilder}

import scala.concurrent.{ExecutionContext, Future}
import org.apache.logging.log4j.scala.Logging
import io.grpc.{ManagedChannel, ManagedChannelBuilder, StatusRuntimeException}
import java.util.concurrent.CountDownLatch
import java.io._
import scala.jdk.CollectionConverters._
import scala.collection.mutable.ListBuffer
import scala.io.Source
import java.util.concurrent.TimeUnit
import scala.concurrent.{Future, Promise}
import utils.{util, Phase, workerPhase}
import generalnet.generalNet.{GeneralnetGrpc,Connect2ServerRequest,Connect2ServerResponse,SortEndMsg2MasterRequest,SortEndMsg2MasterResponse,
  SamplingEndMsg2MasterRequest, SamplingEndMsg2MasterResponse, PartitioningEndMsg2MasterRequest, PartitioningEndMsg2MasterResponse}
import module.{sort,sample,partition}

class workerClient(host: String, port: Int, outputAbsoluteDir : String) extends Logging {
  val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().asInstanceOf[ManagedChannelBuilder[_]].build
  val stub = GeneralnetGrpc.blockingStub(channel)
  val inputAbsolutePath = ListBuffer[String]()
  var totalWorkerNum = -1
  var myWorkerNum = -1
  val workersIPList = ListBuffer[String]()
  var samplesList2Master = ListBuffer[String]()
  var partitionRanges = Array[String]()
  var myPartitionRange = Array[String]("noRangeYet", "noRangeYet")
  def shutdown() = {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
  }

  def addInputAbsolutePath(Path : ListBuffer[String]) = {
    inputAbsolutePath.appendAll(Path)
  }

  def connect2Server(): Unit = {
    val request = Connect2ServerRequest(workerIpAddress = util.getMyIpAddress)
    try{
      val response = stub.connect2Server(request)
      totalWorkerNum = response.workerNum
      myWorkerNum = response.workerID
      workersIPList.appendAll(response.workerIPList)
      for (ip <- workersIPList) {
        logger.info("Worker IP: " + ip)
      }
      logger.info("connect2Server response: " + response.workerID + " " + response.workerNum)
    }
    catch
      {
        case e: StatusRuntimeException =>
          logger.warn(s"RPC failed: ${e.getStatus}")
          return
      }
  }

  def startSort() = {
  val sortInstance = new sort()
  for(path <- inputAbsolutePath){
    sortInstance.sortFile(path)
    }
  }
  def sortEndMsg2Master(): Unit = {
    val request = SortEndMsg2MasterRequest(workerID = myWorkerNum)
    try{
      val response = stub.sortEndMsg2Master(request)
      logger.info("sortEndMsg2Master response: " + response)
    }
    catch
      {
        case e: StatusRuntimeException =>
          logger.warn(s"RPC failed: ${e.getStatus}")
          return
      }
  }

  def startSampling()   = {
    val sampleInstance  = new sample()
    samplesList2Master = sampleInstance.sampleFile(inputAbsolutePath)
  }

  def samplingEndMsg2Master(): Unit = {
    val samples2Master = samplesList2Master.toList.sorted
    val request = SamplingEndMsg2MasterRequest(workerID = myWorkerNum, samples = samples2Master)
    try {
      val response = stub.samplingEndMsg2Master(request)
      logger.info("Sample Range is coming! " + myWorkerNum)
      partitionRanges = partitionRanges.concat(response.totalSamples.toArray)
      for (i <- 0 to response.totalSamples.toList.length - 1) {
        logger.info("PartitionRange[" + i + "] : " + partitionRanges(i))
      }
      myPartitionRange(0) = partitionRanges(2 * myWorkerNum)
      myPartitionRange(1) = partitionRanges(2 * myWorkerNum + 1)
      logger.info("My Partition Range starts from: " + myPartitionRange(0))
      logger.info("My Partition Range ends to: " + myPartitionRange(1))
      /* Save total Samples */
    }
    catch {
      case e: StatusRuntimeException =>
        logger.warn(s"RPC failed: ${e.getStatus}")
        return
    }
  }

  def startPartitioning(): Unit = {
    val partitionInstance = new partition()
    partitionInstance.createWriterForTest(totalWorkerNum)
    for(path <- inputAbsolutePath){
      partitionInstance.partitionEachLine(path, partitionRanges)
    }
    partitionInstance.closeInstWriter()
  }

  def partitioningEndMsg2Master(): Unit = {
    val request = PartitioningEndMsg2MasterRequest(workerID = myWorkerNum)
    try{
      val response = stub.partitioningEndMsg2Master(request)
      logger.info("partitioningEndMsg2Master response: " + response)
    }
    catch
      {
        case e: StatusRuntimeException =>
          logger.warn(s"RPC failed: ${e.getStatus}")
          return
      }
  }
}
