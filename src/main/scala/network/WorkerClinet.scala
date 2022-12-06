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
import generalnet.generalNet.{GeneralnetGrpc,Connect2ServerRequest,Connect2ServerResponse}

class workerClient(host: String, port: Int, outputAbsoluteDir : String) extends Logging {
  val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().asInstanceOf[ManagedChannelBuilder[_]].build
  val stub = GeneralnetGrpc.blockingStub(channel)
  val inputAbsoluteDir = ListBuffer[String]()
  var totalWorkerNum = -1
  var myWorkerNum = -1
  val workersIPList = ListBuffer[String]()
  def shutdown() = {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
  }

  def addInputAbsolutePath(inputAbsolutePath : ListBuffer[String]) = {
    inputAbsoluteDir.appendAll(inputAbsolutePath)
  }

  def connect2Server(): Unit = {
    val request = Connect2ServerRequest(workerIpAddress = util.getMyIpAddress)
    try{
      /* Add Sorting Input File*/
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
}
