package module

import java.io.PrintWriter
import scala.collection.mutable.ListBuffer
import scala.io.Source

class partition{
  private val instWriter = ListBuffer[PrintWriter]()

  def createWriterForTest(numWorker: Int,tempFilePath: String) = {
    for (i<-0 until numWorker){
      instWriter.append(new PrintWriter(tempFilePath +"toMachine." + i.toString))
    }
  }

  def closeInstWriter() = {
    for (writer <- instWriter) {
      writer.close
    }
  }

  def partitionEachLine(path: String, rangeList: Array[String]) = {
    println("Access in " + path)
    val lines = Source.fromFile(path).getLines().map(_.splitAt(10)).toList
    val partitionedLines: ListBuffer[(Int, String)] = ListBuffer()
    for(i<-0 until rangeList.length)
    {
      println(rangeList(i))
    }
    for (line <- lines) {
      var i = 0
      while ((2*i) < rangeList.length) {
        if (line._1 >= rangeList(2*i) && line._1 < rangeList(2*i + 1)) {
          partitionedLines.append((i, line._1 + line._2))
        }
        i = i + 1
      }
      if(line._1 == "~~~~~~~~~~"){
        partitionedLines.append((rangeList.length / 2 - 1, line._1 + line._2))
      }
    }
    for (i <- 0 until instWriter.length) {
      instWriter(i).append(partitionedLines.filter(_._1 == i).map(_._2 + "\r\n").mkString)
    }
  }
}
