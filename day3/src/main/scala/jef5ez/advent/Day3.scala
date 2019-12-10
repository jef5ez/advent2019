package jef5ez.advent

import scala.io.Source

object Day3 extends App {

  val source = Source.fromInputStream(getClass.getResourceAsStream("/day3.txt"))
  val wires = source.getLines().map(line => {
    line.trim.split(',').map(s => (s.head, s.drop(1).toInt))
  }).toIndexedSeq

  val up = (x: Int, y: Int) => (x, y + 1)
  val down = (x: Int, y: Int) => (x, y - 1)
  val left = (x: Int, y: Int) => (x - 1, y)
  val right = (x: Int, y: Int) => (x + 1, y)

  val directions = Map[Char, (Int, Int) => (Int, Int)](
    'U' -> up,
    'D' -> down,
    'L' -> left,
    'R' -> right
  )

  def makeWires(wire: Array[(Char, Int)]) = {
    wire.foldLeft(Seq((0,0))){ case (acc, (dir, steps)) =>
      val dirFunc = directions(dir).tupled
      val startingP = acc.last
      val newSection = Iterator.fill(steps)(dirFunc).scanLeft(startingP){ case (tup, func) => func(tup)}.drop(1)
      acc ++ newSection
    }
  }

  def manhat(tup: (Int, Int)): Int = math.abs(tup._1) + math.abs(tup._2)


  def part1(): Unit = {
    val first = makeWires(wires.head).drop(1)
    println(s"first ten ${first.take(10)}")
    val second = makeWires(wires.tail.head).drop(1)
    val intersection = first.toSet.intersect(second.toSet)
    println(intersection)
    val m = intersection.minBy(manhat)
    println(s"minimum pair $m, ${manhat(m)}")
  }


  def part2(): Unit = {
    val first = makeWires(wires.head).drop(1)
    println(s"first ten ${first.take(10)}")
    val second = makeWires(wires.tail.head).drop(1)
    val intersection = first.toSet.intersect(second.toSet)
    val stepsAround = intersection.map(tup => {
      val aSteps = first.indexOf(tup) + 1
      val bSteps = second.indexOf(tup) + 1
      (tup, aSteps + bSteps)
    })
    val minBoth = stepsAround.minBy(_._2)
    println(s"shortest combined walk $minBoth")
  }


  part1()
  part2()

}
