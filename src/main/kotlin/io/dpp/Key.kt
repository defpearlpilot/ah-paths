package io.dpp


class Key {
  companion object {
    fun of(ch: Char): Key {
      return Key(ch)
    }
  }

  private val _key: Char
  private val _visited: Int

  private constructor(key: Char, visited: Int)
  {
    this._key = key
    this._visited = visited
  }

  private constructor(key: Char): this(key, 0)


  override fun toString(): String
  {
    return "Key('$_key', $_visited)"
  }


  fun traverseAll(): List<Key>?
  {
    val transitions = determineTransitions()
    return transitions[this._key]?.map { ch -> Key(ch, _visited + determineIncrement()) }
  }


  fun traverse(toTraverse: Char): Key? {
    val transitions = determineTransitions()
    val ch = transitions[this._key]?.find { ch -> ch == toTraverse }

    return ch?.let { Key(ch, _visited + determineIncrement()) }
  }


  private fun determineTransitions(): Map<Char, List<Char>>
  {
    return when(_visited) {
      0, 1 -> Transitions.transitions
      else -> Transitions.alphas
    }
  }


  private fun determineIncrement() : Int
  {
    return when {
      Transitions.isVowel(_key) -> 1
      else -> 0
    }
  }

  fun printNonsense(): Unit
  {
    println("Nonsense")
  }

}
