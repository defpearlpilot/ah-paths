package io.dpp

import com.ah.MemoizingRecursiveExpander
import java.math.BigDecimal


object Collector
{
  fun pathsForSequence(maxSequence: Int, letter: Char) {

    fun toKeySequencePair(key: Key, sequence: Int): Pair<Key, BigDecimal>
    {
      return Pair(key, Transitions.pathsForSequenceLength(sequence, key))
    }

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
//    println("A5 " + Expansion(5, Key.of('A')).paths)

//    println(MemoizingRecursiveExpander(2).countPaths('A'))
//    println(MemoizingRecursiveExpander(3).countPaths('A'))
//    println(MemoizingRecursiveExpander(4).countPaths('A'))
//    println(MemoizingRecursiveExpander(5).countPaths('A'))
  }
}