package jef5ez.advent

import scala.io.Source

object Day2 extends App {

  val source = Source.fromInputStream(getClass.getResourceAsStream("/day2.txt"))
  val program = source.getLines().flatMap(_.trim.split(',')).filterNot(_ == "").map(_.toInt).toIndexedSeq

  def part1(): Unit = {
    val state = {
      val copy = program.toArray
      copy.update(1, 12)
      copy.update(2, 2)
      copy
    }
    (state.indices by 4).takeWhile(idx => {
      lazy val Array(a, b, putIdx) = state.slice(idx+1, idx+4)
      state(idx) match {
        case 1 =>
          state.update(putIdx, state(a) + state(b))
          true
        case 2 =>
          state.update(putIdx, state(a) * state(b))
          true
        case 99 => false
        case oops: Any => throw new Exception(s"Bad op code: $oops")
      }
    })
    println(s"part 1 position 0: ${state(0)}")
  }

  def foundMatch(noun: Int, verb: Int, target: Int): Boolean = {
    val state = {
      val copy = program.toArray
      copy.update(1, noun)
      copy.update(2, verb)
      copy
    }
    (state.indices by 4).takeWhile(idx => {
      lazy val Array(a, b, putIdx) = state.slice(idx+1, idx+4)
      state(idx) match {
        case 1 =>
          state.update(putIdx, state(a) + state(b))
          true
        case 2 =>
          state.update(putIdx, state(a) * state(b))
          true
        case 99 => false
        case oops: Any => throw new Exception(s"Bad op code: $oops")
      }
    })
    val done = state(0) == target
    if (done) println(s"part 2: $noun, $verb")
    done
  }

  def part2(): Unit = {
    val pairs = for {
      n <- 0 to 99
      v <- 0 to 99
    } yield (n, v)
    pairs.takeWhile{ case (n, v) => !foundMatch(n, v, 19690720)}
  }


  part1()
  part2()

}
