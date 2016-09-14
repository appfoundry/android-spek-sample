package be.appfoundry.spekdemo.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class JsonWriterUnitTest : Spek({
    describe("A json writer") {
        given("a single value map") {
            val singleValueMap = arrayMapOf(Pair("A", "B"))

            it("should contain A:B") {
                val json = writeToJson(singleValueMap)
                assertThat(json, containsString("\"A\":\"B\""))
            }
        }
    }
})