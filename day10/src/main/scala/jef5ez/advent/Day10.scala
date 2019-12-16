package jef5ez.advent

import scala.io.Source
import scala.collection.JavaConverters._

object Day10 extends App {

  lazy val source = Source.fromInputStream(getClass.getResourceAsStream("/day10.txt"))

  lazy val field: Seq[IndexedSeq[Boolean]] = {
    val lines = source.getLines()
    readInput(lines)
  }

  def readInput(lines: Iterator[String]): List[IndexedSeq[Boolean]] = {
    lines.map(line => {
      line.trim.map(c => c == '#')
    }).toList
  }

  lazy val asteroids = getAsteroids(field)

  def getAsteroids(field: Seq[Seq[Boolean]]) = {
    field.zipWithIndex.flatMap { case (rocks, row) =>
      rocks.zipWithIndex.collect { case (true, col) => (row, col) }
    }
  }

  implicit class CoordOps(val t1: (Int, Int)) extends AnyVal {
    def riseRun(t2: (Int, Int)): (Int, Int) = {
      (t2._1 - t1._1, t2._2 - t1._2)
    }

    def plus(t2: (Int, Int)): (Int, Int) = {
      (t1._1 + t2._1, t1._2 + t2._2)
    }

    def distance(t2: (Int, Int)): Int = {
      manhat((t2._1 - t1._1, t2._2 - t1._2))
    }

    def blocks(t2: (Int, Int), t3: (Int, Int)): Boolean = {
      if (angle(t2) == angle(t3)) {
        distance(t3) > distance(t2)
      } else false
    }

    def angle(t2: (Int, Int)): Double = {
      val a1 = riseRun(t2)
      math.atan2(a1._1, a1._2)
    }
  }

  def manhat(tup: (Int, Int)): Int = math.abs(tup._1) + math.abs(tup._2)

  def getVisibleAsteroids(roids: Seq[(Int, Int)])(sensor: (Int, Int)) = {
    val visible: Map[Double, Seq[(Int, Int)]] = getAngleMap(roids, sensor)
    visible.values.map(_.head).toSet
  }

  private def getAngleMap(roids: Seq[(Int, Int)], sensor: (Int, Int)) = {
    val otherRoids = roids.filterNot(t => sensor == t)
    val sorted = otherRoids.sortBy(t => manhat((sensor._1 - t._1, sensor._2 - t._2)))
    val visible = sorted.foldLeft(Map[Double, Seq[(Int, Int)]]()) { case (acc, seen) =>
      val a = sensor.angle(seen)
      val aligned = acc.getOrElse(a, Seq())
      acc.updated(a, aligned :+ seen)
    }
    visible
  }

  def findBest(roids: Seq[(Int, Int)]): ((Int, Int), Int) = {
    roids.map(sensor => {
      val visible: Set[(Int, Int)] = getVisibleAsteroids(roids)(sensor)
      sensor -> visible.size
    }).maxBy(_._2)
  }

  def part1(): Unit = {
    val bestSensor = findBest(asteroids)
    println(s"part1: $bestSensor")
  }

  def part2(): Unit = {
  }

  part1()
  part2()

}
