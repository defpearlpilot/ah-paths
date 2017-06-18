package io.dpp

import com.ah.MemoizingRecursiveExpander
import java.math.BigDecimal


object Collector
{
  fun pathsForSequence(maxSequence: Int, letter: Char)
  {
    val batching = Batching.forSequence(15)

    val allKeys = Transitions.transitiveKeys(Transitions.KEYBOARD_LETTERS)
    val initialCache = allKeys.map {key -> toKeySequencePair(key, batching.initialBatchSize)}.toMap()
    val preCache = allKeys.map {key -> toKeySequencePair(key, batching.batchSize)}.toMap()

//    println(Transitions.pathMapForSequenceLength(2, Key.of('A')))
    println("A2  " + Expansion(2, Key.of('A')).paths)
    println("AM2 "+ MemoizingRecursiveExpander(1).countPaths('A'))

    println("A3  " + Expansion(3, Key.of('A')).paths)
    println("AM3 "+ MemoizingRecursiveExpander(2).countPaths('A'))

    println("A4 " + Expansion(4, Key.of('A')).paths)
    println("AM4 "+ MemoizingRecursiveExpander(3).countPaths('A'))

    println("A5 " + Expansion(5, Key.of('A')).paths)
    println("AM5 "+ MemoizingRecursiveExpander(4).countPaths('A'))

//    println(MemoizingRecursiveExpander(2).countPaths('A'))
//    println(MemoizingRecursiveExpander(3).countPaths('A'))
//    println(MemoizingRecursiveExpander(4).countPaths('A'))
//    println(MemoizingRecursiveExpander(5).countPaths('A'))
  }


  private fun toKeySequencePair(key: Key, sequence: Int): Pair<Key, BigDecimal>
  {
    return Pair(key, Transitions.pathsForSequenceLength(sequence, key))
  }


  fun pathMapForSequence(maxSequence: Int, letter: Char): Map<Key, BigDecimal>
  {
    val key = Key.of(letter)
    return when (maxSequence) {
      0 -> mapOf()
      1 -> mapOf(Pair(key, BigDecimal.ONE))
      else -> pathMapForKeys(2, setOf(key))
    }
  }


  private fun pathMapForSequence(maxSequence: Int, keys: Set<Key>)
  {
    val allKeys = Transitions.transitiveKeys(Transitions.KEYBOARD_LETTERS)
    val initialCache = allKeys.map {key -> toKeySequencePair(key, 2)}.toMap()

    initialCache.forEach {(k, count) ->
      println("For $k the count is $count")
    }
  }


  fun pathMapForKeys(currentSequence: Int, keys: Set<Key>): Map<Key, BigDecimal>
  {
//    fun expand(currentSequence: Int, byKeyList: Map<Key, List<Key>>): Map<Key, BigDecimal>
//    {
//      return when(currentSequence)
//      {
//        maxSequence -> flatten(byKeyList)
//        else ->
//        {
//          val grouped = byKeyList.values.flatMap { paths -> paths.flatMap { it.traverseAll() } }.groupBy { it -> it }
//          expand(currentSequence + 1, grouped)
//        }
//      }
//    }
//
//    /*
//    *
//    * */
    val byKeyList = keys.flatMap{ it.traverseAll() }.groupBy { it -> it }
    return flatten(byKeyList)
  }


  private fun flatten(map: Map<Key, List<Key>>): Map<Key, BigDecimal>
  {
    val flattened = map.entries.map{ (k, v) -> Pair(k, BigDecimal(v.size)) }.toMap()
//    val byChar = flattened.entries.groupBy { it -> it.key.key() }
    return flattened
  }

}