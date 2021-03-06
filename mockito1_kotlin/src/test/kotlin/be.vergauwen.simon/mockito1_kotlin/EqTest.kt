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

import be.vergauwen.simon.mockito1_kotlin.eq
import be.vergauwen.simon.mockito1_kotlin.mock
import com.nhaarman.expect.expect
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EqTest {

    private val interfaceInstance: MyInterface = MyClass()
    private val openClassInstance: MyClass = MyClass()
    private val closedClassInstance: ClosedClass = ClosedClass()

    private lateinit var doAnswer: Open

    @Before
    fun setup() {
        /* Create a proper Mockito state */
        doAnswer = Mockito.doAnswer { }.`when`(mock())
    }

    @After
    fun tearDown() {
        /* Close `any` Mockito state */
        doAnswer.go(0)
    }

    @Test
    fun eqInterfaceInstance() {
        /* When */
        val result = eq(interfaceInstance)

        /* Then */
        expect(result).toNotBeNull()
    }

    @Test
    fun eqOpenClassInstance() {
        /* When */
        val result = eq(openClassInstance)

        /* Then */
        expect(result).toNotBeNull()
    }

    @Test
    fun eqClosedClassInstance() {
        /* When */
        val result = eq(closedClassInstance)

        /* Then */
        expect(result).toNotBeNull()
    }

    private interface MyInterface
    private open class MyClass : MyInterface
    class ClosedClass
}

