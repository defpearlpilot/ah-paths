package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object TransitionSpec: Spek({
  describe("transition suite")
  {
    on("transitive keys from a")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('A'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(33)
      }
    }

    on("transitive keys from e")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('E'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(33)
      }
    }

    on("transitive keys from I")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('I'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(33)
      }
    }

    on("transitive keys from O")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('O'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(33)
      }
    }

    on("transitive keys from 1")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('1'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(50)
      }
    }

    on("transitive keys from 2")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('2'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(50)
      }
    }

    on("transitive keys from 3")
    {
      val transitiveKeys = Transitions.transitiveKeys(setOf('3'))
      it("should return the result of adding the first number to the second number")
      {
        expect(transitiveKeys.size).to.equal(50)
      }
    }
  }
})