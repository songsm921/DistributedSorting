package network

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}
import java.util.concurrent.CountDownLatch
import io.grpc.{Server, ServerBuilder}
import utils.util.getMyIpAddress
import scala.collection.mutable.ListBuffer
import generalnet.generalNet.{Connect2ServerRequest, Connect2ServerResponse,SortEndMsg2MasterRequest,SortEndMsg2MasterResponse, GeneralnetGrpc, SamplingEndMsg2MasterRequest, SamplingEndMsg2MasterResponse}

object MasterServer{
  var numFinishGetSamples = 0
  val totalSampleList = ListBuffer[String]()
}
class MasterServer(executionContext: ExecutionContext, val numClient: Int, val Port: Int) extends Logging {
  self =>
  private[this] var server: Server = null
  private val clientLatch: CountDownLatch = new CountDownLatch(numClient)
  private val sortLatch: CountDownLatch = new CountDownLatch(numClient)
  private val sampleLatch: CountDownLatch = new CountDownLatch(numClient)
  private val workerIPList : ListBuffer[String] = ListBuffer[String]()
  def start() = {
    server = ServerBuilder.forPort(Port).addService(GeneralnetGrpc.bindService(new GeneralnetImpl, executionContext)).build.start
    logger.info("Server numClient: " + self.numClient)
    logger.info("Server started, listening on " + Port)
    sys.addShutdownHook {
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  def printEndpoint(): Unit = {
    System.out.println(getMyIpAddress + ":" + Port)
  }
  def stop() = {
    if (server != null) {
      server.shutdown()
    }
  }
  def blockUntilShutdown() = {
    if (server != null) {
      server.awaitTermination()
    }
  }
  private class GeneralnetImpl extends GeneralnetGrpc.Generalnet {
    override def connect2Server(request: Connect2ServerRequest): Future[Connect2ServerResponse] = {
      val _workerID_ = workerIPList.length
      workerIPList.append(request.workerIpAddress)
      logger.info("Worker IP: " + request.workerIpAddress + "added")
      clientLatch.countDown()
      clientLatch.await()
      val response = Connect2ServerResponse(workerID = _workerID_,workerNum = numClient,workerIPList = workerIPList.toList)
      Future.successful(response)
    }

    override def sortEndMsg2Master(request: SortEndMsg2MasterRequest): Future[SortEndMsg2MasterResponse] = {
      logger.info("Sorted Finished from Worker: " + request.workerID)
      sortLatch.countDown()
      sortLatch.await()
      val response = SortEndMsg2MasterResponse(startNext = 1)
      Future.successful(response)
    }

    override def samplingEndMsg2Master(request: SamplingEndMsg2MasterRequest): Future[SamplingEndMsg2MasterResponse] = {
      logger.info("Sampling Finished from Worker: " + request.workerID)
      while(MasterServer.numFinishGetSamples < numClient){
       if(MasterServer.numFinishGetSamples == request.workerID){
         MasterServer.totalSampleList.appendAll(request.samples)
         MasterServer.numFinishGetSamples += 1
         sampleLatch.countDown()
       }
       else{
          Thread.sleep(100)
       }
      }
      sampleLatch.await()
      val rangeEachMachine : ListBuffer[(String,String)] = ListBuffer[(String,String)]()
      val firstRange = " " * 10
      val lastRange = "~" * 10
      for(i<-1 to numClient){
        if(i == 1){
          rangeEachMachine.append((firstRange,MasterServer.totalSampleList((MasterServer.totalSampleList.length/numClient) * i - 1)))
        }
        else if(i == numClient){
          rangeEachMachine.append((rangeEachMachine(i-2)._2,lastRange))
        }
        else{
          rangeEachMachine.append((rangeEachMachine(i-2)._2,MasterServer.totalSampleList((MasterServer.totalSampleList.length/numClient) * i - 1)))
        }
      }
      for(range <- rangeEachMachine){
        println(range._1 + " " + range._2)
      }
      val _rangeSequence = rangeEachMachine.toList.flatten{case (a,b)=>List(a,b)}
      val response = SamplingEndMsg2MasterResponse(totalSamples = _rangeSequence)
      Future.successful(response)
    }
  }
}