package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object SimpleSpec: Spek({
  describe("test")
  {
    val letterA = Key.of('A')

    on("traversal from a") {
      val paths = letterA.traverseAll()
      it("should return the result of adding the first number to the second number") {
        expect(2).to.equal(paths?.size)
      }
    }
  }
})