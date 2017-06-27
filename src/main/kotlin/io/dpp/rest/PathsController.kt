package io.dpp.rest

import io.dpp.Collector
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class PathsController
{
  @RequestMapping("/paths")
  fun pathsIndividual(@RequestParam(value = "letters") letters: List<Char>,
                      @RequestParam(value = "sequence") sequence: Int): Map<Char, BigDecimal>
  {
    val pathCountMap = letters.map { letter ->
      val paths = Collector.pathCountForChar(sequence, letter)
      Pair(letter, paths)
    }.toMap()

    return pathCountMap
  }


  @RequestMapping("/paths2")
  fun pathsOptimized(@RequestParam(value = "letters") letters: List<Char>,
                     @RequestParam(value = "sequence") sequence: Int): Map<Char, BigDecimal>
  {
    val pathCounts = Collector.pathCountsForChars(sequence, letters)
    println("DONE")
    return pathCounts
  }

}