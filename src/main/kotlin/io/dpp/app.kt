package io.dpp

class App
{
  companion object
  {
    @JvmStatic
    fun main(args: Array<String>)
    {
//      println(Transitions.alphas)

      println(Key.of('A').traverseAll())
    }
  }
}