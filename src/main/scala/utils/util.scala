package utils

import java.net.{DatagramSocket, InetAddress}
import scala.collection.mutable.ListBuffer
import scala.io.Source
import java.io.File
object util {
  def getMyIpAddress: String = {
    val socket = new DatagramSocket
    try {
      socket.connect(InetAddress.getByName("8.8.8.8"), 10002)
      socket.getLocalAddress.getHostAddress
    } finally if (socket != null) socket.close()
  }
  def parseArguments(args: Array[String]) = {
    val argList = ListBuffer(args: _ *)
    val masterInfo = argList.remove(0)
    val masterIP = masterInfo.split(":")(0)
    val masterPort = masterInfo.split(":")(1).toInt
    argList.remove(0) // remove -I
    val inputDirectoryList = ListBuffer[String]()
    while(argList.head != "-O"){
      inputDirectoryList.append(argList.remove(0))
    }
    argList.remove(0) // remove -O
    val outputPath = argList.remove(0)
    (masterIP, masterPort, inputDirectoryList, outputPath)
  }

  def splitFileper4MB(path: String,startLines: Int) = {
      val targetList = ListBuffer[String]()
      val lines = Source.fromFile(path).getLines().toList
      var isFinish = 0
      val destLines = startLines + 40000
      if(destLines >= lines.length){
        for(i <- startLines until lines.length){
          targetList.append(lines(i))
        }
        isFinish = 1
      }
      else{
        for (i <- startLines to destLines) {
          targetList.append(lines(i))
        }
        isFinish = 0
      }
      if(isFinish == 0){
        (targetList,destLines + 1)
      }
      else{
        (targetList,-1)
      }

  }
}

object Phase extends Enumeration {
  val INITIAL = Value(0)
  val FRAGMENTATION = Value(1)
  val SORTING = Value(2)
  val SAMPLING = Value(3)
  val MERGING = Value(4)
  val BALANCING = Value(5)
  val TERMINATING = Value(6)
}

object workerPhase extends Enumeration{
  val INITIAL = Value(0)
  val FRAGMENTATION = Value(1)
  val SORTING = Value(2)
  val SAMPLING = Value(3)
  val MERGING = Value(4)
  val BALANCING = Value(5)
  val TERMINATING = Value(6)
}
