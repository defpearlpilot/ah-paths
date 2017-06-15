package io.dpp

class App
{
  companion object
  {
    @JvmStatic
    fun main(args: Array<String>)
    {
//      println(Transitions.alphas)
      println("Hello")
      println(Key.of('A'))
      println(Key.of('A').traverse('L')?.traverse('A')?.traverse('L')?.traverseAll())
    }
  }
}