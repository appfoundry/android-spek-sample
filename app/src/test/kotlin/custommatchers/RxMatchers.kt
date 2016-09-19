package custommatchers

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeDiagnosingMatcher
import rx.observers.TestSubscriber

fun <T> onNextEvents(itemMatchers: Matcher<*>) =
        object : TypeSafeDiagnosingMatcher<TestSubscriber<T>>() {
            override fun describeTo(description: Description) {
                itemMatchers.describeTo(description)
            }

            override fun matchesSafely(subscriber: TestSubscriber<T>, mismatchDescription: Description): Boolean {
                itemMatchers.describeMismatch(subscriber.onNextEvents, mismatchDescription)
                return itemMatchers.matches(subscriber.onNextEvents)
            }
        }

fun <T> isCompleted(): Matcher<TestSubscriber<T>> =
        object : TypeSafeDiagnosingMatcher<TestSubscriber<T>>() {
            override fun describeTo(description: Description) {
                description.appendText("Subscriber completed")
            }

            override fun matchesSafely(item: TestSubscriber<T>, mismatchDescription: Description): Boolean {
                when (item.onCompletedEvents.size) {
                    0 -> mismatchDescription.appendText("Subscriber never completed ")
                    1 -> Unit
                    else -> mismatchDescription.appendText("Completed multiple times: ${item.onCompletedEvents.size}")
                }
                return item.onCompletedEvents.size.equals(1)
            }
        }

fun <T> hasNoErrors(): Matcher<TestSubscriber<T>> =
        object : TypeSafeDiagnosingMatcher<TestSubscriber<T>>() {
            override fun describeTo(description: Description) {
                description.appendText("Subscriber without errors")
            }

            override fun matchesSafely(subscriber: TestSubscriber<T>, mismatchDescription: Description): Boolean {
                mismatchDescription.appendText("was with errors ").appendThrowables(subscriber.onErrorEvents)
                return subscriber.onErrorEvents.size == 0
            }
        }

fun <T> hasError(type: Class<*>): Matcher<TestSubscriber<T>> =
        object : TypeSafeDiagnosingMatcher<TestSubscriber<T>>() {
            override fun describeTo(description: Description) {
                description.appendText("Subscriber with ${type.name}")
            }

            override fun matchesSafely(subscriber: TestSubscriber<T>, mismatchDescription: Description): Boolean {
                mismatchDescription.appendText("Subscriber without errors")
                return subscriber.onErrorEvents.size == 1
            }
        }

private fun <T : Throwable> Description.appendThrowables(items: List<T>) {
    if (items.size == 0) return
    this.appendText("[ ")
    for (i in 0..items.size - 2) {
        this.appendValue(items[i]).appendText(" , ")
    }
    this.appendValue(items[items.size - 1]).appendText(" ]")
}
