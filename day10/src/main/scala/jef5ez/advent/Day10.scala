package jef5ez.advent

import scala.io.Source
import scala.collection.JavaConverters._

object Day10 extends App {

  val source = Source.fromInputStream(getClass.getResourceAsStream("/day10.txt"))

  val field = source.getLines().map(line => {
    line.trim.map(c => c == '#')
  }).toList

  val asteriods = field.zipWithIndex.flatMap{ case (rocks, row) =>
    rocks.zipWithIndex.collect{ case (true, col) => (row, col)}
  }


  def part1(): Unit = {
    println(asteriods)
  }


  def part2(): Unit = {
  }

  part1()
  part2()

}
