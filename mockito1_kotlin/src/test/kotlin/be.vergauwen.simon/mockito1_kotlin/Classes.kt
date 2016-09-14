package be.vergauwen.simon.mockito1_kotlin/*
* The MIT License
*
* Copyright (c) 2016 Niek Haarman
* Copyright (c) 2007 Mockito contributors
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

open class Open {
    open fun go(vararg arg: Any?) {
    }

    open fun modifiesContents(a: IntArray) {
        for (i in 0..a.size - 1) {
            a[i] = a[i] + 1
        }
    }

    open fun stringResult() = "Default"
}

class Closed

interface Methods {

    fun intArray(i: IntArray)
    fun closed(c: Closed)
    fun closedArray(a: Array<Closed>)
    fun closedNullableArray(a: Array<Closed?>)
    fun closedCollection(c: Collection<Closed>)
    fun closedList(c: List<Closed>)
    fun closedStringMap(m: Map<Closed, String>)
    fun closedSet(s: Set<Closed>)
    fun string(s: String)
    fun closedVararg(vararg c: Closed)
    fun throwableClass(t: ThrowableClass)

    fun stringResult(): String
}

class ThrowableClass(cause: Throwable) : Throwable(cause)
