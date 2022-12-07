package main
import utils.{Phase, util, workerPhase}
import network.workerClient
import network.MasterServer
import network.WorkerServer
import network.tempClient

import java.io._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext

object worker {
  val workerPort = 18219
  def main(args: Array[String]): Unit = {
    var barrier= 0
    val parameter = util.parseArguments(args)
    val masterIP = parameter._1
    val masterPort = parameter._2
    val inputDirectoryList = parameter._3
    val outputPath = parameter._4
    val client = new workerClient(masterIP,masterPort,outputPath)
    // make method in Util module next
    for (inputDirectory <- inputDirectoryList){
      val dir =  new File(inputDirectory)
      val files = dir.listFiles.filter(_.isFile).toList
      val inputAbsolutePath = files.map {x => x.toString}
      client.addInputAbsolutePath(inputAbsolutePath.to(ListBuffer))
    }
    try{
      client.connect2Server()
      client.startSort()
      client.sortEndMsg2Master()

      client.startSampling()
      client.samplingEndMsg2Master()

      client.startPartitioning(inputDirectoryList(0))
      client.partitioningEndMsg2Master()
      println("Let's start shuffling")
      /*Start Shuffling*/
      for(i <- 0 until client.totalWorkerNum){
        val serverWorkerID = client.startShufflingMsg2Master(i)
        /*println(serverWorkerID + "and" + client.myWorkerNum)*/
        if(i == client.myWorkerNum){
          val workerserver = new WorkerServer(ExecutionContext.global,client.totalWorkerNum,workerPort+i,outputPath,client.myWorkerNum,inputDirectoryList(0))
          workerserver.start()
          barrier = 1
          var check = 1
          while(check == 1){
            if(workerserver.isShutdown == 1){
              check = 0
            }
            /*else{
              Thread.sleep(10)
            }*/
          }
          workerserver.stop()
          //util.copyOwnData(i,inputDirectoryList(0)+"toMachine."+ i.toString,outputPath) // shutdownWorkerServer에 녹일 수도 있을듯.
        }
        else{
          while(barrier == 0){}
          val client2client = new tempClient(client.workersIPList(i),workerPort + i,outputPath,client.myWorkerNum)
          /*serverWorkerID로 보낼 toMachine.i 파일 split*/
          var isSplitFinish = 0
          var startLines = 0
          while(isSplitFinish == 0){
            val content:(ListBuffer[String],Int) = util.splitFileper4MB(inputDirectoryList(0)+"toMachine."+i.toString,startLines)
            if(content._2 == -1){
              isSplitFinish = 1
            }

            else{
              startLines = content._2
            }

            client2client.Shuffle(content._1,isSplitFinish)
          }
          client2client.ShutdownWorkerServer()
          barrier = 0
        }
      }
      println("End of Shuffling")
    }
    catch {
      case e: Exception => println("Exception: " + e)
    }
    finally {
      client.shutdown()
    }
  }
}
