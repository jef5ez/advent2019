package jef5ez.advent

import scala.io.Source

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

  def comparePositions(pairs: Seq[(Moon, Moon)]): (Boolean, Boolean, Boolean) = {
    val x = pairs.forall{ case (cur, init) =>
      cur.position(0) == init.position(0) && cur.velocity(0) == init.velocity(0)
    }
    val y = pairs.forall{ case (cur, init) =>
      cur.position(1) == init.position(1) && cur.velocity(1) == init.velocity(1)
    }
    val z = pairs.forall{ case (cur, init) =>
      cur.position(2) == init.position(2) && cur.velocity(2) == init.velocity(2)
    }
    (x, y, z)
  }


  def leastCommonMultiple(nums: Array[Long]): Long = {
    nums match {
      case Array(x, y, z) =>
        lcm(x, lcm(y, z))
    }
  }

  def lcm(a: Long, b: Long) = a * (b / gcd(a, b))

  def gcd(a: Long, b: Long) = BigInt(a).gcd(BigInt(b)).toLong

  def part2(): Unit = {
    val starting = readInput()
    val moons = readInput()
    val pairs = moons.zip(starting)
    val repeatedSteps = Array.fill(3)(Option.empty[Long])
    var step = 0L
    def updateRepeats(idx: Int, name: String): Unit = {
      if (repeatedSteps(idx).isEmpty) {
        repeatedSteps.update(idx, Some(step))
        println(s"found first repeated $name at step $step")
      } else println(s"found another repeated $name at step $step")
    }

    while (repeatedSteps.exists(op => op.isEmpty)) {
      step += 1
      moveMoons(moons)
      val (x, y, z) = comparePositions(pairs)
      if (x) {
        updateRepeats(0, "x")
      }
      if (y) {
        updateRepeats(1, "y")
      }
      if (z) {
        updateRepeats(2, "z")
      }
    }
    val allSteps = repeatedSteps.flatten
    println(s"All repeating intervals: ${allSteps.mkString(",")}")
    println(s"lcm ${leastCommonMultiple(allSteps.take(3))}")
  }

  part1()
  part2()

}
