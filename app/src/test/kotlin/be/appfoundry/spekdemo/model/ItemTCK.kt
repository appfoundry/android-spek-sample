package be.appfoundry.spekdemo.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.vergauwen.simon.mockito1_kotlin.any
import be.vergauwen.simon.mockito1_kotlin.mock
import be.vergauwen.simon.mockito1_kotlin.whenever
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnitPlatform::class)
@PrepareForTest(ContextCompat::class)
abstract class ItemTCK(val factory: () -> Item) : Spek({
    val item = factory()
    describe("handling an item object") {
        val context = mock<Context>()
        val drawable = mock<Drawable>()
        beforeEach {
            mockStatic(ContextCompat::class.java)
            whenever(ContextCompat.getDrawable(any<Context>(), any<Int>()))
                    .thenReturn(drawable)
        }

        it("should never have a null name") {
            assertThat(item.name, notNullValue())
        }
        it("should always have a color id") {
            assertThat(item.itemColorId, isA(Int::class.java))
        }
        it("should always return a drawable") {
            assertThat(item.getIcon(context), equalTo(drawable))
        }
    }
})

class URLItemTCKTest : ItemTCK({ URLItem("www.appfoundry.be") })

class TwitterItemTCKTest : ItemTCK({ TwitterItem("@AppFoundryBE") })

class MailItemTCKTest : ItemTCK({ MailItem("simon.vergauwen@appfoundry.be") })

class PhoneItemTCKTest : ItemTCK({ PhoneItem("003238719966") })


// Without factory lambda

//@RunWith(PowerMockRunner::class)
//@PowerMockRunnerDelegate(JUnitPlatform::class)
//@PrepareForTest(ContextCompat::class)
//abstract class ItemTCK(val item: Item) : Spek({
//    describe("handling an item object") {
//        val context = mock<Context>()
//        val drawable = mock<Drawable>()
//        beforeEach {
//            mockStatic(ContextCompat::class.java)
//            whenever(ContextCompat.getDrawable(any<Context>(), any<Int>()))
//                    .thenReturn(drawable)
//        }
//
//        it("should never have a null name") {
//            assertThat(item.name, notNullValue())
//        }
//        it("should always have a color id") {
//            assertThat(item.itemColorId, isA(Int::class.java))
//        }
//        it("should always return a drawable") {
//            assertThat(item.getIcon(context), equalTo(drawable))
//        }
//    }
//})
//
//class URLItemTCKTest : ItemTCK(URLItem("www.appfoundry.be"))
//
//class TwitterItemTCKTest : ItemTCK(TwitterItem("@AppFoundryBE"))
//
//class MailItemTCKTest : ItemTCK(MailItem("simon.vergauwen@appfoundry.be"))
//
//class PhoneItemTCKTest : ItemTCK(PhoneItem("003238719966"))