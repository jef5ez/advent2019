package jef5ez.advent

object Day4 extends App {


  val start = "246515"
  val startDigits = start.map(_.toInt)
  val end = "739105"
  val endDigits = end.map(_.toInt)

  def hasConsecutives(lst: Seq[Int]): Boolean = {
    lst.zip(lst.drop(1)).exists(tup => tup._1 == tup._2)
  }

  def hasSinglePair(lst: Seq[Int]): Boolean = {
    val doubles = lst.zip(lst.drop(1)).filter(tup => tup._1 == tup._2)
    val triples = lst.sliding(3,1).toSeq.filter(s => s.toSet.size == 1)
    doubles.map(_._1).toSet.diff(triples.map(_.head).toSet).nonEmpty
  }

  def isAscending(lst: Seq[Int]): Boolean = {
    lst.zip(lst.drop(1)).forall(tup => tup._1 <= tup._2)
  }

  def part1(): Unit = {
    val passing = for {
      i <- (start.toInt to end.toInt)
      lst = i.toString.map(_.toInt)
      if hasConsecutives(lst) && isAscending(lst)
    } yield i
    println(s"found ${passing.size} possible passwords")
  }


  def part2(): Unit = {
    val passing = for {
      i <- (start.toInt to end.toInt)
      lst = i.toString.map(_.toInt)
      if hasSinglePair(lst) && isAscending(lst)
    } yield i
    println(s"found ${passing.size} possible passwords")
    println(s"examples ${passing.take(10)}")
  }


  part1()
  part2()

}
