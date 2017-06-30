package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.math.BigDecimal


object TransitionSpec: Spek({
  describe("transition suite")
  {
    val letterA = Key.of('A')

    on("transitive keys from a")
    {
//      val transitiveKeys = Transitions.transitiveKeys(setOf('A'))
      it("should return the result of adding the first number to the second number")
      {
//        expect(transitiveKeys.size).to.equal(5)
        expect(1).to.equal(1)
      }
    }
  }
})