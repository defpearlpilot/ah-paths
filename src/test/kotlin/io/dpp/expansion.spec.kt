package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.math.BigDecimal


object ExpansionSpec: Spek({
 describe("Expansion class testing suite")
 {
   val letterA = Key.of('A')

   on("expansion of size zero")
   {
     val paths = Expansion(0, letterA).paths
     it("should be zero") {
       expect(paths).to.be.equal(BigDecimal.ZERO)
     }
   }

   on("expansion of size one")
   {
     val paths = Expansion(1, letterA).paths
     it("should be one") {
       expect(paths).to.be.equal(BigDecimal.ONE)
     }
   }

   on("expansion of size two")
   {
     val pathMap = Expansion(2, letterA).pathMap
     it("should") {
       expect(pathMap[Key.visited('L')]).to.be.equal(BigDecimal.ONE)
       expect(pathMap[Key.visited('H')]).to.be.equal(BigDecimal.ONE)
     }
   }
 }
})