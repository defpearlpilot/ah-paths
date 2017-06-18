package io.dpp

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
      println(Collector.pathsForSequence(5, 'A'))


    }
  }
}