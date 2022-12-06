package main
import utils.{Phase, util, workerPhase}
import network.workerClient
import java.io._
import scala.collection.mutable.ListBuffer


object worker {
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
    }
    catch {
      case e: Exception => println("Exception: " + e)
    }
    finally {
      client.shutdown()
    }
  }
}
