package be.appfoundry.spekdemo.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xit
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class JsonWriterSpekTest : Spek({
    context("a json writer") {

        given("a single value map") {
            val singleValueMap = arrayMapOf(Pair("A", "B"))

            it("should contain A:B") {
                val json = writeToJson(singleValueMap)
                assertThat(json, containsString("\"A\":\"B\""))
            }

            xit("should contain A:B",reason = "pending version 2") {
                val json = writeToJson(singleValueMap)
                assertThat(json, containsString("\"A\":\"B\""))
            }
        }
    }
})