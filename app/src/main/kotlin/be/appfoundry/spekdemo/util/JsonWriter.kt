@file:JvmName("JsonUtil")
package be.appfoundry.spekdemo.util

import android.support.v4.util.ArrayMap
import android.util.JsonWriter
import java.io.StringWriter

fun writeToJson(values: ArrayMap<String, String>): String {
    val stringWriter = StringWriter()

    JsonWriter(stringWriter).apply {
        beginObject()
        values.forEach {
            name(it.key)
            value(it.value)
        }
        endObject()
        flush()
    }

    return stringWriter.toString()
}