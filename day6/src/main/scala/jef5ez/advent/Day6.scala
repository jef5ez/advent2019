package jef5ez.advent

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.{AsUndirectedGraph, DefaultEdge, SimpleDirectedGraph}
import org.jgrapht.traverse.TopologicalOrderIterator

import scala.io.Source
import scala.collection.JavaConverters._

object Day6 extends App {

//  case class Edge(source: String, target: String)
  val source = Source.fromInputStream(getClass.getResourceAsStream("/day6.txt"))
  val edges = source.getLines().map(line => {
    line.trim.split(')')
  }).toIndexedSeq

  val g = {
    val builder = SimpleDirectedGraph.createBuilder[String, DefaultEdge](classOf[DefaultEdge])
    edges.foreach{ case Array(a, b) =>
      builder.addEdge(a, b)
    }
    builder.build()
  }


  def part1(): Unit = {
    println(s"read in ${g.vertexSet().size()} vertices and ${g.edgeSet().size} edges")

    val topoIter = new TopologicalOrderIterator[String, DefaultEdge](g).asScala
    val startMap = Map[String, Int](topoIter.next() -> 0)
    val depths = topoIter.foldLeft(startMap){ case (acc, next) =>
        val incoming = g.incomingEdgesOf(next).asScala.head
        val parent = g.getEdgeSource(incoming)
        val parentDepth = acc.getOrElse(parent, 0)
        acc.updated(next, parentDepth + 1)
    }
    println(s"Some examples ${depths.take(10)}")
    println(s"sum of depths: ${depths.values.sum}")
  }


  def part2(): Unit = {
    val sp = new DijkstraShortestPath(new AsUndirectedGraph(g))
    val path = sp.getPath("YOU", "SAN")
    println(s"shortestPath length ${path.getLength - 2}")//subtract 2 because start and end don't count
  }

  part1()
  part2()

}
