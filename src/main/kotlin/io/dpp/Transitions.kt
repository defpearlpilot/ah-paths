package io.dpp

import java.math.BigDecimal


object Transitions
{
  val KEYBOARD_LETTERS = setOf('A', 'E', 'I', 'O', 'U', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', '1', '2', '3')

  val VOWELS = setOf('A', 'E', 'I', 'O', 'U')

  fun isVowel(ch: Char): Boolean
  {
    return VOWELS.contains(ch)
  }

  val TRANSITIONS = mapOf(Pair('A', listOf('L', 'H')),
                          Pair('E', listOf('H', 'N')),
                          Pair('I', listOf('2', 'L', 'B')),
                          Pair('O', listOf('2', 'H', 'D')),
                          /* consonant TRANSITIONS */
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

  val ALPHAS = TRANSITIONS.entries.map { (key, value) -> Pair(key, value.filter { ch -> !isVowel(ch) }) }.toMap()


  fun pathsForSequenceLength(maxSequence: Int, letters: Set<Char>): BigDecimal
  {
    val keys = letters.map { letter -> Key.of(letter) }.toSet()
    return when (maxSequence) {
      0 -> BigDecimal.ZERO
      1 -> BigDecimal(keys.size)
      else -> pathsForKeys(maxSequence, 2, keys)
    }
  }


  fun pathsForSequenceLength(maxSequence: Int, key: Key): BigDecimal
  {
    return when (maxSequence) {
      0 -> BigDecimal.ZERO
      1 -> BigDecimal.ONE
      else -> pathsForKey(maxSequence, 2, key)
    }
  }


  fun transitiveKeys(letters: Set<Char>): Set<Key>
  {
    fun getKeys(keys: Set<Key>): Set<Key>
    {
      fun keysRecursively(traversedKeys: Set<Key>): Set<Key>
      {
        return when(keys.containsAll(traversedKeys)) {
          true -> return keys
          else -> getKeys(traversedKeys)
        }
      }

      val traversedKeys = keys.flatMap { it.traverseAll() }.toSet()
      return keysRecursively(traversedKeys)
    }

    val keys = letters.map{ letter -> Key.of(letter) }.toSet()
    return getKeys(keys)
  }


  private fun pathsForKeys(maxSequence: Int, currentSequence: Int, keys: Set<Key>): BigDecimal
  {
    val groupedKeys = keys.flatMap { it.traverseAll() }.groupBy { it -> it }

    return when (currentSequence) {
      maxSequence -> countGrouped(groupedKeys)
      else -> countRecursively(maxSequence, currentSequence, groupedKeys)
    }
  }


  private fun pathsForKey(maxSequence: Int, currentSequence: Int, key: Key): BigDecimal
  {
    val groupedKeys = mapOf(Pair(key, key.traverseAll()))

    return when (currentSequence) {
      maxSequence -> countGrouped(groupedKeys)
      else -> countRecursively(maxSequence, currentSequence, groupedKeys)
    }
  }


  private fun countRecursively(maxSequence: Int, currentSequence: Int, groupedKeys: Map<Key, List<Key>>): BigDecimal
  {
    val aggregateCount = groupedKeys.keys.map { Pair(it, pathsForKeys(maxSequence, currentSequence + 1, setOf(it))) }.toMap()
    return countGrouped(groupedKeys, aggregateCount)
  }


  private fun countGrouped(groupedKeys: Map<Key, List<Key>>): BigDecimal
  {
    return groupedKeys.entries.fold(BigDecimal.ZERO, {acc, entry -> acc.add(BigDecimal(entry.value.size)) })
  }


  private fun countGrouped(groupedKeys: Map<Key, List<Key>>, aggregate: Map<Key, BigDecimal>): BigDecimal
  {
    fun count(acc: BigDecimal, entry: Map.Entry<Key, List<Key>>): BigDecimal
    {
      return acc.add(aggregate[entry.key]?.multiply(BigDecimal(entry.value.size)))
    }

    return groupedKeys.entries.fold(BigDecimal.ZERO, ::count)
  }


}