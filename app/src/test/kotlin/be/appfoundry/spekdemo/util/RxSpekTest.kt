package be.appfoundry.spekdemo.util

import custommatchers.RxAssertJ.assertThat
import custommatchers.RxAssertJ.assertThatASubscriberTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import rx.observers.TestSubscriber
import spek.onRx

//@RunWith(JUnitPlatform::class)
//class RxSpekTest : Spek({
//    onRx("Testing rx code"){
//        on("when testing something observed by an android schedulers") {
//            var testSubscriber = TestSubscriber<Long>()
//            beforeEach { testSubscriber = TestSubscriber<Long>() }
//
//            it("should be easily tested in unit test") {
//                doSomeRxing().subscribe(testSubscriber)
//                assertThat(testSubscriber,
//                        allOf(hasNoErrors(),
//                                onNextEvents(hasSize<Any>(1)),
//                                onNextEvents(hasItem(1L)),
//                                isCompleted()))
//            }
//        }
//    }
//
//    given("when listening to slow async code") {
//        var testSubscriber = TestSubscriber<Boolean>()
//        beforeEach { testSubscriber = TestSubscriber<Boolean>() }
//
//        it("s output should be easy to test") {
//            doSomeLongRxing().toBlocking().subscribe(testSubscriber)
//            assertThat(testSubscriber,
//                    allOf(hasNoErrors(),
//                            onNextEvents(hasSize<Any>(1)),
//                            onNextEvents(hasItem(true)),
//                            isCompleted()))
//        }
//    }
//})


@RunWith(JUnitPlatform::class)
class RxSpekTest : Spek({
    onRx("Testing rx code"){
        on("when testing something observed by an android schedulers") {
            var testSubscriber = TestSubscriber<Long>()
            beforeEach { testSubscriber = TestSubscriber<Long>() }

            it("should be easily tested in unit test") {
                doSomeRxing().subscribe(testSubscriber)
                assertThat(testSubscriber)
                        .hasNoErrors()
                        .isCompleted()
                        .hasReceivedCount(1)
                        .hasReceived(1L)
            }
        }
    }

    given("when listening to slow async code") {
        it("s output should be easy to test") {
            assertThatASubscriberTo(doSomeLongRxing().toBlocking())
                    .hasNoErrors()
                    .hasReceived(true)
                    .hasReceivedCount(1)
                    .isCompleted()
        }
    }
})