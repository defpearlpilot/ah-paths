package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.math.BigDecimal


object KeySpec: Spek({
  describe("test")
  {
    val letterA = Key.of('A')

    on("traversal from a") {
      val paths = letterA.traverseAll()
      it("should return the result of adding the first number to the second number") {
        expect(2).to.equal(paths.size)
      }
    }
  }

  describe("suite") {
    val someANotVisited = Key.of('A')
    val otherANotVisited = Key.of('A')
    val someAVisited = Key.visited('A')
    val someAExhausted = Key.exhausted('A')

    expect(someANotVisited).to.equal(otherANotVisited)
    expect(someANotVisited).to.not.equal(someAVisited)
    expect(someANotVisited).to.not.equal(someAExhausted)
  }

  describe("blah") {
    val someANotVisited = Key.of('A')
    val someAVisited = Key.visited('A')
    val someAExhausted = Key.exhausted('A')

    val map = mapOf(Pair(someAVisited, BigDecimal.ONE))
    expect(map[someAVisited]).to.equal(BigDecimal.ONE)
  }
})