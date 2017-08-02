package io.dpp

import com.ah.MemoRecursiveExpander
import com.ah.TraversedNode
import java.math.BigDecimal


fun toKeyExpansionPair(key: Key, sequence: Int): Pair<Key, Expansion>
{
  println("Calculating expansion for $sequence $key")
  return Pair(key, Expansion(sequence, key))
}


class ExpansionCache(private val size: Int)
{
  val cache = mutableMapOf<Key, Expansion>()


  fun warm(keys: Set<Key>)
  {
    keys.forEach { key -> cache.put(key, Expansion(size, key)) }
  }

  fun expansionFor(key: Key): Expansion
  {
    val expansion = cache[key]
    if (expansion == null) {
      val computed = Expansion(size, key)
      cache.put(key, computed)
      return computed
    }

    return expansion
  }
}

object Collector
{


  fun pathCountForChar(maxSequence: Int, letter: Char): BigDecimal
  {
    return pathCountForKey(maxSequence, Key.of(letter))
  }


  fun pathCountForKey(maxSequence: Int, key: Key): BigDecimal
  {
    return pathMapForKey(maxSequence, key).values.fold(BigDecimal.ZERO, { b1, b2 -> b1.add(b2) })
  }


  fun pathCountsForChars(maxSequence: Int, letters: List<Char>): Map<Char, BigDecimal>
  {
    val keys = letters.map { l -> Key.of(l) }
    return pathCountsForKeys(maxSequence, keys).entries.map{ it -> Pair(it.key.key(), it.value) }.toMap()
  }


  fun pathCountsForKeys(maxSequence: Int, keys: List<Key>): Map<Key, BigDecimal>
  {
    return pathCountsForSequences(maxSequence, keys)
  }



  private fun pathMapForKey(maxSequence: Int, key: Key): Map<Key, BigDecimal>
  {
    return when (maxSequence) {
      0 -> mapOf()
      1 -> mapOf(Pair(key, BigDecimal.ONE))
      else -> pathMapForSequence(maxSequence, key)
    }
  }


  private fun pathMapForSequence(maxSequence: Int, key: Key): Map<Key, BigDecimal>
  {
    val batching = Batching.forSequence(maxSequence)
    val batchSize = batching.batchSize
    println("Batching $batching")

    // warm with an initial set of transitive keys
    val expansions = ExpansionCache(batchSize)
    expansions.warm(Transitions.transitiveKeys(Transitions.KEYBOARD_LETTERS))

    fun _customExpansion(size: Int, key: Key): Expansion = Expansion(size, key)
    fun _warmedExpansion(size: Int, key: Key): Expansion = expansions.expansionFor(key)

    return _pathMapForSequence(maxSequence, key, batchSize, ::_warmedExpansion, ::_customExpansion)
  }


  private fun pathCountsForSequences(maxSequence: Int, keys: List<Key>): Map<Key, BigDecimal>
  {
    val batching = Batching.forSequence(maxSequence)
    val batchSize = batching.batchSize
    println("Batching $batching")

    // warm with an initial set of transitive keys
    val expansions = ExpansionCache(batchSize)
    expansions.warm(Transitions.transitiveKeys(Transitions.KEYBOARD_LETTERS))

    fun _customExpansion(size: Int, key: Key): Expansion = Expansion(size, key)
    fun _warmedExpansion(size: Int, key: Key): Expansion = expansions.expansionFor(key)

    return keys.map { key ->
      val pathMap = _pathMapForSequence(maxSequence, key, batchSize, ::_warmedExpansion, ::_customExpansion)
      Pair(key, sumSizes(pathMap))
    }.toMap()
  }


  private fun _pathMapForSequence(maxSequence: Int,
                                  key: Key,
                                  batchSize: Int,
                                  batchExpProvider: (size: Int, key: Key) -> Expansion,
                                  remainExpProvider: (size: Int, key: Key) -> Expansion): Map<Key, BigDecimal>
  {

    fun expand(currentSequence: Int, keyMap: Map<Key, BigDecimal>): Map<Key, BigDecimal>
    {

      fun _expand(size: Int, keyMap: Map<Key, BigDecimal>, expansionProv: (size: Int, key: Key) -> Expansion): Map<Key, BigDecimal>
      {
        val expandedPairs = keyMap.entries.map { (key, count) ->
          val expansion = expansionProv.invoke(size, key)
          val expanded = expand(currentSequence + size - 1, expansion.pathMap)
          val expandedCount = sumSizes(expanded)
          val factor = count.multiply(expandedCount)

          Pair(key, factor)
        }

        return expandedPairs.toMap()
      }

      return when {
        (maxSequence == currentSequence) -> {
          keyMap
        }
        (maxSequence - currentSequence < batchSize) -> {
          val spread = maxSequence - currentSequence + 1
          return _expand(spread, keyMap, remainExpProvider)
        }
        else -> {
          return _expand(batchSize, keyMap, batchExpProvider)
        }
      }
    }

    val expansion = batchExpProvider.invoke(batchSize, key)
    return expand(batchSize, expansion.pathMap)
  }


  private fun sumSizes(map: Map<Key, BigDecimal>): BigDecimal
  {
    return map.values.reduce{ acc, size -> acc.add(size) }
  }


  fun memoExpand(sequence: Int, letter: Char): Long {
    val grouped = MemoRecursiveExpander.captureExpansion(sequence, TraversedNode(letter))
    return Collector.sumTraversed(grouped)
  }


  fun sumTraversed(groupedKeys: Map<TraversedNode, Long>): Long
  {
    return groupedKeys.values.reduce{ acc, count -> acc + count}
  }


}