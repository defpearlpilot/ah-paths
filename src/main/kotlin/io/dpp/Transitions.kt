package io.dpp

import java.math.BigDecimal
import java.math.BigInteger


object Transitions
{
  val keys = setOf('A', 'E', 'I', 'O', 'U', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', '1', '2', '3')

  val vowels = setOf('A', 'E', 'I', 'O', 'U')

  fun isVowel(ch: Char): Boolean
  {
    return vowels.contains(ch)
  }

  val transitions = mapOf(Pair('A', listOf('L', 'H')),
                          Pair('E', listOf('H', 'N')),
                          Pair('I', listOf('2', 'L', 'B')),
                          Pair('O', listOf('2', 'H', 'D')),
                          /* consonant transitions */
                          Pair('B', listOf('K', 'M', 'I')),
                          Pair('C', listOf('F', 'L', 'N', 'J')),
                          Pair('D', listOf('G', 'M', 'O')),
                          Pair('F', listOf('C', 'M', 'I')),
                          Pair('G', listOf('D', 'N', '2')),
                          Pair('H', listOf('A', 'K', '1', '3', 'O', 'E')),
                          Pair('J', listOf('3', 'M', 'C')),
                          Pair('K', listOf('B', 'H', '2')),
                          Pair('L', listOf('A', 'C', 'I', '3')),
                          Pair('M', listOf('F', 'B', 'D', 'J')),
                          Pair('N', listOf('1', 'G', 'C', 'E')),
                          Pair('1', listOf('F', 'H', 'N')),
                          Pair('2', listOf('K', 'G', 'I', 'O')),
                          Pair('3', listOf('L', 'H', 'J')))

  val alphas = transitions.entries.map { (key, value) -> Pair(key, value.filter { ch -> !isVowel(ch) } ) }.toMap()

  fun count(depth: Int, letters: Set<Char>): BigDecimal
  {
//    letters.flatMap {  }
    return BigDecimal(0)
  }
}