package be.appfoundry.spekdemo.util

import android.support.v4.util.ArrayMap

fun <K, V> arrayMapOf(vararg pairs: Pair<K, V>): ArrayMap<K, V> = ArrayMap<K, V>(pairs.size).apply { putAll(pairs) }