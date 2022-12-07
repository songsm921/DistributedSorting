package network

import org.apache.logging.log4j.scala.Logging

import scala.concurrent.{ExecutionContext, Future}
import java.util.concurrent.CountDownLatch
import io.grpc.{Server, ServerBuilder}

import scala.collection.mutable.ListBuffer
import generalnet.generalNet.{Connect2ServerRequest, Connect2ServerResponse,SortEndMsg2MasterRequest,SortEndMsg2MasterResponse, GeneralnetGrpc}
import utils.util.getMyIpAddress
class MasterServer(executionContext: ExecutionContext, val numClient: Int, val Port: Int) extends Logging {
  self =>
  private[this] var server: Server = null
  private val clientLatch: CountDownLatch = new CountDownLatch(numClient)
  private val sortLatch: CountDownLatch = new CountDownLatch(numClient)
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
  }
}