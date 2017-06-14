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
    return "Key(" + this._key + ")"
  }

  fun traverseAll(): List<Key>
  {
    return Transitions.transitions[this._key]!!.map { ch -> Key(ch, this._visited + 1) }
  }


}
