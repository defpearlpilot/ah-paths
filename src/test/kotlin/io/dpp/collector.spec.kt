package io.dpp

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.math.BigDecimal


object CollectorSpec: Spek({
 describe("Expansion class testing suite")
 {
   val letterA = Key.of('A')

   on("expansion of size zero")
   {
     val size = Collector.pathCountForKey(5, Key.of('A'))
     it("should be zero") {
       println(size)
       expect(size).to.be.equal(BigDecimal(95))
     }
   }

 }
})