package io.dpp

import java.math.BigDecimal


class Expansion(val maxSequence: Int, val key: Key)
{

  val pathMap = pathMapForSequenceLength()
  val paths = pathMap.values.reduce { acc, count -> acc.add(count) }

  fun pathMapForSequenceLength(): Map<Key, BigDecimal>
  {
    return when (maxSequence) {
      0 -> mapOf()
      1 -> mapOf(Pair(key, BigDecimal.ONE))
      else -> pathMapForKeys(2, setOf(key))
    }
  }


  fun pathMapForKeys(currentSequence: Int, keys: Set<Key>): Map<Key, BigDecimal>
  {
    fun expand(currentSequence: Int, byKeyList: Map<Key, List<Key>>): Map<Key, BigDecimal>
    {
      return when(currentSequence)
      {
        maxSequence ->
        {
          flatten(byKeyList)
        }
        else ->
        {
          val grouped = byKeyList.values.flatMap { paths -> paths.flatMap { it.traverseAll() } }.groupBy { it -> it }
          expand(currentSequence + 1, grouped)
        }
      }
    }

    /*
    *
    * */
    val byKeyList = keys.flatMap{ it.traverseAll() }.groupBy { it -> it }

    return when(currentSequence)
    {
      maxSequence -> flatten(byKeyList)
      else -> expand(currentSequence, byKeyList)
    }
  }


  private fun flatten(map: Map<Key, List<Key>>): Map<Key, BigDecimal>
  {
    val flattened = map.entries.map{ (k, v) -> Pair(k, BigDecimal(v.size)) }.toMap()
    val byChar = flattened.entries.groupBy { it -> it.key.key() }
    return flattened
  }


}