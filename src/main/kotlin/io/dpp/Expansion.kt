package io.dpp

import java.math.BigDecimal


class Expansion(val maxSequence: Int, val key: Key)
{
  val pathMap = pathMapForSequenceLength()
  val paths = pathMap.values.fold(BigDecimal.ZERO, { acc, count -> acc.add(count) })

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
        maxSequence -> flatten(byKeyList)
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
    return expand(currentSequence, byKeyList)
  }

  override fun toString(): String
  {
    return "Expansion($key, $maxSequence, #$paths, ${pathMap.keys})"
  }

  private fun flatten(map: Map<Key, List<Key>>): Map<Key, BigDecimal>
  {
    return map.entries.map{ (k, v) -> Pair(k, BigDecimal(v.size)) }.toMap()
  }

}