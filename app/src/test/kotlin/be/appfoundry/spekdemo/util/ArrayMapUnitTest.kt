package be.appfoundry.spekdemo.util

import android.support.v4.util.ArrayMap
import custommatchers.IsMapWithSize
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class ArrayMapUnitTest : Spek({
    context("When constructing a array map") {
        val pair1 = Pair("key1", "value1")

        on("constructing a array map with one value") {
            val singlePairMap = arrayMapOf(pair1)

            it("should have a capacity of 1") {
                MatcherAssert.assertThat(singlePairMap, Matchers.allOf(IsMapWithSize.aMapWithSize(1), IsMapWithSize.hasEntry(pair1)))
            }
        }

        on("construction of a map with multiple values") {
            val pair2 = Pair("key2", "value2")
            val doublePairMap = arrayMapOf(pair1, pair2)

            it("should be a map with 2 pairs"){
                MatcherAssert.assertThat(doublePairMap, Matchers.allOf(IsMapWithSize.aMapWithSize(2), IsMapWithSize.hasEntry(pair1), IsMapWithSize.hasEntry(pair2)))
            }
        }

        on("constructing an empty map"){
            val emptyMap : ArrayMap<String, String> = arrayMapOf()

            it("should be an empty map"){
                MatcherAssert.assertThat(emptyMap, IsMapWithSize.anEmptyMap())
            }
        }
    }
})