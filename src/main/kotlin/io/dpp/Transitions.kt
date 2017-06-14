package io.dpp


object Transitions
{
  val vowels = setOf('A', 'E', 'I', 'O', 'U')

  fun isVowel(ch: Char): Boolean
  {
    return vowels.contains(ch)
  }

  val transitions = mapOf(Pair('A', arrayOf('L', 'H')),
                          Pair('E', arrayOf('H', 'N')),
                          Pair('I', arrayOf('2', 'L', 'B')),
                          Pair('O', arrayOf('2', 'H', 'D')),
                          /* consonant transitions */
                          Pair('B', arrayOf('K', 'M', 'I')),
                          Pair('C', arrayOf('F', 'L', 'N', 'J')),
                          Pair('D', arrayOf('G', 'M', 'O')),
                          Pair('F', arrayOf('C', 'M', 'I')),
                          Pair('G', arrayOf('D', 'N', '2')),
                          Pair('H', arrayOf('A', 'K', '1', '3', 'O', 'E')),
                          Pair('J', arrayOf('3', 'M', 'C')),
                          Pair('K', arrayOf('B', 'H', '2')),
                          Pair('L', arrayOf('A', 'C', 'I', '3')),
                          Pair('M', arrayOf('F', 'B', 'D', 'J')),
                          Pair('N', arrayOf('1', 'G', 'C', 'E')),
                          Pair('1', arrayOf('F', 'H', 'N')),
                          Pair('2', arrayOf('K', 'G', 'I', 'O')),
                          Pair('3', arrayOf('L', 'H', 'J')))

  val alphas = transitions.entries.map { (key, value) -> Pair(key, value.filter { ch -> !isVowel(ch) } ) }.toMap()
}