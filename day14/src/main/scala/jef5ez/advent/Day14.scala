package jef5ez.advent

import scala.io.Source

object Day14 extends App {

  def readPair(str: String): (Long, String) = {
    val lst = str.split(" ")
    (lst.head.toLong, lst.tail.head)
  }

  def readInput() = {
    lazy val source = Source.fromInputStream(getClass.getResourceAsStream("/day14.txt"))
    val lines = source.getLines().filterNot(_ == "")
    lines.map(l => {
      val reaction = l.split(" => ")
      val end = readPair(reaction.last)
      val inputs = reaction.head.split(", ").map(readPair)
      assert(inputs.nonEmpty, s"somehow this is empty $l")
      (inputs, end)
    }).toArray
  }

  lazy val reactions = readInput().map{ case (ins, out) =>
    out._2 -> (out._1, ins)
  }.toMap

  def part1(): Unit = {
    val ore: Long = findNeededOre(1)
    println(s"ores needed: ${ore}")
  }

  def findNeededOre(fuel: Long): Long = {
    val onDeck = scala.collection.mutable.Queue("FUEL")
    val needed = scala.collection.mutable.Map[String, Long]("FUEL" -> fuel)
    val totals = scala.collection.mutable.Map[String, Long]()
    while (onDeck.exists(_ != "ORE")) {
      val current = onDeck.head
      val need = needed(current)
      val total = totals.getOrElseUpdate(current, 0L)
      if (need <= total || current == "ORE") onDeck.dequeue()
      else {
        reactions.get(current).foreach { case (produced, ingredients) =>
          totals.update(current, total + produced)
          ingredients.foreach { case (n, ing) =>
            needed.update(ing, needed.getOrElse(ing, 0L) + n)
            if (!onDeck.contains(ing)) {
              onDeck.enqueue(ing)
            }
          }
        }
      }
      if (current == "ORE") println("tried to lookup ore")
    }
    val ore = needed("ORE")
    ore
  }
  def part2(): Unit = {
    val maxOre = 1e12.longValue()
    var fuel = 1L
    var newFuel = 1L
    var ore = findNeededOre(fuel)
    do {
      println(s"more than $newFuel")
      fuel = newFuel
      ore = findNeededOre(fuel+1L)
      newFuel = math.max(fuel+1L, math.floor((fuel + 1L) * maxOre / ore).toLong)
    } while (ore < maxOre)

  }

  part1()
//  part2()

}
