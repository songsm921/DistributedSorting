package main
import utils.{Phase, util, workerPhase}
import network.workerClient
import network.MasterServer

import java.io._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext


object worker {
  val workerPort = 18219
  def main(args: Array[String]): Unit = {
    val parameter = util.parseArguments(args)
    val masterIP = parameter._1
    val masterPort = parameter._2
    val inputDirectoryList = parameter._3
    val outputPath = parameter._4
    val client = new workerClient(masterIP,masterPort,outputPath)
    for (inputDirectory <- inputDirectoryList){
      val dir =  new File(inputDirectory)
      val files = dir.listFiles.filter(_.isFile).toList
      val inputAbsolutePath = files.map {x => x.toString}
      client.addInputAbsolutePath(inputAbsolutePath.to(ListBuffer))
    }
    try{
      client.connect2Server()
      val server = new MasterServer(ExecutionContext.global,client.totalWorkerNum-1,workerPort)
      server.start()
      server.printEndpoint()
      for(i<-0 until client.totalWorkerNum){
        if(i != client.myWorkerNum){
          val worker2worker = new workerClient(client.workersIPList(i),workerPort,outputPath)
          worker2worker.connect2Server()
        }

      }
    }
    catch {
      case e: Exception => println("Exception: " + e)
    }
    finally {
      client.shutdown()
    }
  }
}
