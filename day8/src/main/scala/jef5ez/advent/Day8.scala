package jef5ez.advent

import scala.io.Source
import scala.collection.JavaConverters._

object Day8 extends App {

//  case class Edge(source: String, target: String)
  val source = Source.fromInputStream(getClass.getResourceAsStream("/day8.txt"))
  val pixels = source.getLines().flatMap(line => {
    line.trim.map(_.toString.toInt)
  }).toIndexedSeq

  val rows = 6
  val cols = 25
  val pixelsPerLayer = rows * cols

  def part1(): Unit = {
    val leastZeros = pixels.grouped(pixelsPerLayer).toArray.minBy(_.count(p => p == 0))
    println(leastZeros)
    val ans = leastZeros.count(_ == 1) * leastZeros.count(_ == 2)
    println(s"ones times twos: ${ans}")
  }


  def part2(): Unit = {
    val img = pixels.grouped(pixelsPerLayer).toArray.map(layer => layer.grouped(cols).toArray)
    val result = Array.fill(rows, cols)(2)
    for {
      r <- 0 until rows
      c <- 0 until cols
    } {
      var l = 0
      while (result(r)(c) == 2) {
        result(r).update(c, img(l)(r)(c))
        l += 1
      }
    }
    println(result.map(_.mkString("")).mkString("\n").replace("0", " "))
  }

  part1()
  part2()

}
