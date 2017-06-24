package io.dpp


class Key {
  companion object {
    fun of(ch: Char): Key {
      return Key(ch)
    }

    fun visited(ch: Char): Key {
      return Key(ch, 1)
    }

    fun exhausted(ch: Char): Key {
      return Key(ch, 2)
    }
  }

  private val _key: Char
  private val _visited: Int

  private constructor(key: Char, visited: Int)
  {
    this._key = key
    this._visited = when (visited) {
      0, 1 -> visited
      else -> 2
    }
  }

  private constructor(key: Char): this(key, 0)


  fun key(): Char
  {
    return _key
  }


  override fun toString(): String
  {
    return "Key('$_key', $_visited)"
  }


  override fun hashCode(): Int
  {
    return _key.hashCode() + _visited.hashCode()
  }


  override fun equals(other: Any?): Boolean
  {
    if (other == null)
    {
      return false
    }

    if (other !is Key)
    {
      return false
    }

    return _key == other._key && _visited == other._visited
  }


  fun traverseAll(): List<Key>
  {
    val transitions = determineTransitions()
    return transitions.getOrDefault(_key, listOf()).map { ch -> Key(ch, _visited + determineIncrement()) }
  }


  fun traverse(toTraverse: Char): Key? {
    val transitions = determineTransitions()
    val ch = transitions[this._key]?.find { ch -> ch == toTraverse }

    return ch?.let { Key(ch, _visited + determineIncrement()) }
  }


  private fun determineTransitions(): Map<Char, List<Char>>
  {
    return when(_visited) {
      0, 1 -> Transitions.TRANSITIONS
      else -> Transitions.ALPHAS
    }
  }


  /**
   * If we are traversing from this key to another, we increment if we are a vowel
   */
  private fun determineIncrement() : Int
  {
    return when {
      Transitions.isVowel(_key) -> 1
      else -> 0
    }
  }

}
