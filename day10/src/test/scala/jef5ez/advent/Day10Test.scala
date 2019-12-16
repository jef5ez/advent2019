package jef5ez.advent

import org.specs2.mutable.SpecificationWithJUnit
import jef5ez.advent.Day10._

class Day10Test extends SpecificationWithJUnit {

  "blocking checker" should {
    "work on straight lines" >> {
      (0,0).blocks((1,0), (2,0)) must beTrue
      (0,0).blocks((2,0), (3,0)) must beTrue
      (0,0).blocks((0,1), (0,2)) must beTrue
      (3,4).blocks((3,6), (3,8)) must beTrue
      (4,3).blocks((3,3), (2,3)) must beTrue

      (0,0).blocks((0,1), (1,0)) must beFalse
      (0,0).blocks((1,0), (0,3)) must beFalse
      (0,0).blocks((1,0), (1,1)) must beFalse
      (0,0).blocks((0,1), (1,1)) must beFalse
      (0,0).blocks((6,0), (1,0)) must beFalse
      (3, 3).blocks((2, 3), (4, 3)) must beFalse
      (3, 3).blocks((3, 2), (3, 4)) must beFalse
    }

    "work on diagonals" >> {
      (1,1).blocks((2,2), (5,5)) must beTrue
      (1,1).blocks((2,3), (3,5)) must beTrue

      (0,0).blocks((1,1), (0,3)) must beFalse
      (0,0).blocks((1,1), (3,0)) must beFalse
      (0,0).blocks((2,3), (0,3)) must beFalse
      (0,0).blocks((0,3), (2,3)) must beFalse
    }
  }

  "getVisible" should {
    "work on (0,0)" >> {
      val vis = getVisibleAsteroids(asteroids)((0,0))
      vis must contain((1,0))
      vis must contain((0,3))
    }
  }

  "small examples" should {
    "find 33 asteroids at (8, 5)" >> {
      val example = """
                      |......#.#.
                      |#..#.#....
                      |..#######.
                      |.#.#.###..
                      |.#..#.....
                      |..#....#.#
                      |#..#....#.
                      |.##.#..###
                      |##...#..#.
                      |.#....####
                      |""".stripMargin
      val roids = getAsteroids(readInput(example.split("\n").iterator.filterNot(_ == "")))
      val (tup, count) = findBest(roids)
      "correct count" >> {
        count mustEqual(33)
      }
      "correct location" >> {
        tup mustEqual((8, 5))
      }
    }
  }

}
