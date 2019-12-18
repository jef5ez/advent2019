package jef5ez.advent

import scala.io.Source
import scala.collection.JavaConverters._

object Day12 extends App {

  class Moon(x:Int, y: Int, z: Int) {
    val position = Array(x, y, z)
    val velocity = Array(0, 0, 0)

    def getEnergy: Int = {
      position.map(math.abs).sum * velocity.map(math.abs).sum
    }

    def applyGravity(otherMoon: Moon): Unit = {
      position.zip(otherMoon.position).zipWithIndex.foreach { case ((p1, p2), idx) =>
        if (p1 > p2) {
          velocity.update(idx, velocity(idx) - 1)
        } else if (p1 < p2) {
          velocity.update(idx, velocity(idx) + 1)
        }
      }
    }

    def applyVelocity(): Unit = {
      velocity.zipWithIndex.foreach { case (vi, idx) =>
        position.update(idx, position(idx) + vi)
      }
    }

    override def toString: String = {
      val pos = position.mkString("(", ",", ")")
      val vel = velocity.mkString("(", ",", ")")
      s"$hashCode: Pos: ${pos}, Vel: $vel"
    }
  }


  val regex = """<x=([-\d]+), y=([-\d]+), z=([-\d]+)>""".r("x", "y", "z")
  def readInput() = {
    lazy val source = Source.fromInputStream(getClass.getResourceAsStream("/day12.txt"))
    val lst = source.getLines().map(_.trim).filterNot(_ == "").flatMap(l => {
      regex.findFirstMatchIn(l).map(m => {
        new Moon(m.group("x").toInt, m.group("y").toInt, m.group("z").toInt)
      })
    }).toList
    source.close()
    lst
  }

  def moveMoons(moons: Seq[Moon]): Unit = {
    for {
      m1 <- moons
      m2 <- moons if m1 != m2
    } {
      m1.applyGravity(m2)
    }
    moons.foreach(_.applyVelocity())
  }

  def part1(): Unit = {
    val moons = readInput()
    (0 until 1000).foreach(_ => moveMoons(moons))
    println(moons.mkString("\n"))
    val energy = moons.map(_.getEnergy).sum
    println(s"Total Energy: $energy")
  }

  def part2(): Unit = {
  }

  part1()
  part2()

}
