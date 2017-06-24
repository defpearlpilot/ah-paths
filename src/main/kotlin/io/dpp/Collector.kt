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
    keys.forEach(
        { key ->
          val expansion = Expansion(size, key)
//          println("Warming cache $key $expansion")
          cache.put(key, expansion)
        })

  }

  fun expansionFor(key: Key): Expansion
  {
    val expansion = cache[key]
    if (expansion == null) {
      val computed = Expansion(size, key)
      cache.put(key, computed)
//      println("Computed expansion for $key $computed ${cache[key]}")
      return computed
    }

//    println("Expansion for $key $expansion")
    return expansion
  }
}

object Collector
{
  fun pathsForSequence(maxSequence: Int, letter: Char): BigDecimal
  {
    return pathsForSequence(maxSequence, Key.of(letter))
  }


  fun pathsForSequence(maxSequence: Int, key: Key): BigDecimal
  {
    return Expansion(maxSequence, key).paths
  }


  fun pathCountForChar(maxSequence: Int, letter: Char): BigDecimal
  {
    return pathCountForKey(maxSequence, Key.of(letter))
  }


  fun pathCountForKey(maxSequence: Int, key: Key): BigDecimal
  {
    return pathMapForKey(maxSequence, key).values.fold(BigDecimal.ZERO, { b1, b2 -> b1.add(b2) })
  }


  fun pathMapForChar(maxSequence: Int, letter: Char): Map<Key, BigDecimal>
  {
    return pathMapForKey(maxSequence, Key.of(letter))
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

    fun expand(currentSequence: Int, keyMap: Map<Key, BigDecimal>): Map<Key, BigDecimal>
    {
      fun _expand(size: Int, keyMap: Map<Key, BigDecimal>, expansionProv: (size: Int, key: Key) -> Expansion) {
        val expandedPairs = keyMap.entries.map { (key, count) ->
          val expansion = expansionProv.invoke(size, key)
          val expanded = expand(currentSequence + size - 1, expansion.pathMap)
          val expandedCount = sumSizes(expanded)
          val factor = count.multiply(expandedCount)

          Pair(key, factor)
        }

        expandedPairs.toMap()
      }

//      println("Current sequence $currentSequence")
      return when {
        (maxSequence == currentSequence) -> {
          keyMap
        }
        (maxSequence - currentSequence < batchSize) -> {
          val spread = maxSequence - currentSequence + 1

          val expandedPairs = keyMap.entries.map { (key, count) ->
//            println("${" ".repeat(currentSequence)} $currentSequence $spread $key $count")

            val expansion = Expansion(spread, key)
//            println("${" ".repeat(currentSequence)} $expansion")
            val expanded = expand(currentSequence + spread - 1, expansion.pathMap)
            val expandedCount = sumSizes(expanded)
            val factor = count.multiply(expandedCount)

//            println("${" ".repeat(currentSequence)} $currentSequence $key C$count => F$factor")
            Pair(key, factor)
          }

          expandedPairs.toMap()
        }
        else -> {
          val expandedPairs = keyMap.entries.map { (key, count) ->
            val expansion = expansions.expansionFor(key)
            val expanded = expand(currentSequence + batchSize - 1, expansion.pathMap)
            val expandedCount = sumSizes(expanded)
            val factor = count.multiply(expandedCount)
//            println("${" ".repeat(currentSequence)} $currentSequence $key C$count => F$factor")
            Pair(key, factor)
          }

          expandedPairs.toMap()
        }
      }
    }

    val expansion = expansions.expansionFor(key)
    return expand(batchSize, expansion.pathMap)
  }


  private fun flattenListsToSizes(map: Map<Key, List<Key>>): Map<Key, BigDecimal>
  {
    return map.entries.map{ (k, v) -> Pair(k, BigDecimal(v.size)) }.toMap()
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