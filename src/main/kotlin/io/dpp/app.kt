package io.dpp

import com.ah.MemoRecursiveExpander
import com.ah.TraversedNode

class App
{
  companion object
  {
    @JvmStatic
    fun main(args: Array<String>)
    {
//      println(Transitions.ALPHAS)
//      println(Transitions.pathsForSequenceLength(0, Transitions.KEYBOARD_LETTERS))
//      println(Transitions.pathsForSequenceLength(1, Transitions.KEYBOARD_LETTERS))
//      println(Transitions.pathsForSequenceLength(2, Transitions.KEYBOARD_LETTERS))
//      println(Transitions.pathsForSequenceLength(3, Transitions.KEYBOARD_LETTERS))
//      println(Collector.pathCountForKey(5, Key.of('A')))
//      println(Collector.pathCountForKey(5, Key.visited('A')))
//      println(Collector.pathCountForKey(5, Key.exhausted('A')))

      println(Collector.pathCountForKey(3, Key.of('A')))
      println(MemoRecursiveExpander(2).countPaths('A'))


      println(Collector.pathCountForKey(3, Key.of('L')))
      println(MemoRecursiveExpander(2).countPaths('L'))

//      println(Collector.pathCountForKey(5, Key.of('L')))
//      println(MemoRecursiveExpander(4).countPaths('L'))
//
//
//      println(Collector.pathCountForKey(10, Key.of('L')))
//      println(MemoRecursiveExpander(9).countPaths('L'))
//
//      println(Collector.pathCountForKey(11, Key.of('L')))
//      println(MemoRecursiveExpander(10).countPaths('L'))
//
      println(Collector.pathCountForKey(10, Key.of('L')))
      println(Collector.pathsForSequence(10, Key.of('L')))
      println(Collector.memoExpand(9, 'L'))

//      Transitions.ALPHAS.forEach { key ->
//        println(Collector.pathsForSequence(5, key.key))
//        println(MemoRecursiveExpander(4).countPaths(key.key))
//      }
//      println(Collector.pathMapForSequence(5, 'A'))
//      println(Collector.pathMapForSequence(5, 'L'))

      /*
      archaeological?
      java?  don't seem to like it?

      object oriented


       */

    }
  }
}