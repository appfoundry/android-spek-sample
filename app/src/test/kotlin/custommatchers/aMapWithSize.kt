package custommatchers

import org.hamcrest.FeatureMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.collection.IsMapContaining

class IsMapWithSize<K, V>(sizeMatcher: Matcher<in Int>) : FeatureMatcher<Map<out K, V>, Int>(sizeMatcher, "a map with size", "map size") {

    override fun featureValueOf(actual: Map<out K, V>): Int? = actual.size

    companion object {
        fun <K, V> aMapWithSize(sizeMatcher: Matcher<in Int>): Matcher<Map<out K, V>> = IsMapWithSize(sizeMatcher)

        fun <K, V> aMapWithSize(size: Int): Matcher<Map<out K, V>> = IsMapWithSize.aMapWithSize<K, V>(Matchers.equalTo(size))

        fun <K, V> anEmptyMap(): Matcher<Map<out K, V>> = IsMapWithSize.aMapWithSize(Matchers.equalTo(0))

        fun <K, V> hasEntry(pair: Pair<K,V>): Matcher<Map<out K, V>> = IsMapContaining.hasEntry(pair.first, pair.second)
    }
}