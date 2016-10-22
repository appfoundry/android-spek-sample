package custommatchers

import org.assertj.core.api.AbstractObjectAssert
import rx.Observable
import rx.observables.BlockingObservable
import rx.observers.TestSubscriber

object RxAssertJ {
    fun <T> assertThat(subscriber: TestSubscriber<T>): TestSubscriberAssert<T> = TestSubscriberAssert(subscriber)

    fun <T> assertThatASubscriberTo(observable: Observable<T>): TestSubscriberAssert<T> {
        val subscriber = TestSubscriber<T>()
        observable.subscribe(subscriber)
        return TestSubscriberAssert(subscriber)
    }

    fun <T> assertThatASubscriberTo(observable: BlockingObservable<T>): TestSubscriberAssert<T> {
        val subscriber = TestSubscriber<T>()
        observable.subscribe(subscriber)
        return TestSubscriberAssert(subscriber)
    }
}


class TestSubscriberAssert<T>(actual: TestSubscriber<T>) : AbstractObjectAssert<TestSubscriberAssert<T>, TestSubscriber<T>>(actual, TestSubscriberAssert::class.java) {

    /**
     * Asserts that this {@code Subscriber} is unsubscribed.
     *
     * @throws AssertionError if this {@code Subscriber} is not unsubscribed
     */
    fun isUnsubscribed() : TestSubscriberAssert<T> {
        actual.assertUnsubscribed()
        return this
    }

    /**
     * Asserts that the received onNext events, in order, are the specified items.
     *
     * @param values the items to check
     * @throws AssertionError if the items emitted do not exactly match those specified by {@code values}
     */
    fun hasReceived(vararg values: T): TestSubscriberAssert<T> {
        actual.assertValues(*values)
        return this
    }

    fun hasReceivedCount(count : Int) : TestSubscriberAssert<T> {
        actual.assertValueCount(count)
        return this
    }

    /**
     * Asserts that there are no onNext events received.
     *
     * @throws AssertionError if there were any onNext events
     */
    fun receivedNothing() : TestSubscriberAssert<T> {
        actual.assertNoValues()
        return this
    }

    /**
     * Asserts that there is exactly one completion event.
     *
     * @throws AssertionError if there were zero, or more than one, onCompleted events
     */
    fun isCompleted() : TestSubscriberAssert<T> {
        actual.assertCompleted()
        return this
    }

    /**
     * Asserts that there is no completion event.
     *
     * @throws AssertionError if there were one or more than one onCompleted events
     */
    fun isNotCompleted() : TestSubscriberAssert<T> {
        actual.assertNotCompleted()
        return this
    }

    /**
     * Asserts that this {@code Subscriber} has received no {@code onError} notifications.
     *
     * @throws AssertionError if this {@code Subscriber} has received one or more {@code onError} notifications
     */
    fun hasNoErrors() : TestSubscriberAssert<T> {
        actual.assertNoErrors()
        return this
    }

    /**
     * Asserts that there is exactly one error event which is a subclass of the given class.
     *
     * @param clazz the class to check the error against.
     * @throws AssertionError if there were zero, or more than one, onError events, or if the single onError event did not carry an error of a subclass of the given class
     */
    fun hasError(clazz: Class<out Throwable>) : TestSubscriberAssert<T> {
        actual.assertError(clazz)
        return this
    }
}