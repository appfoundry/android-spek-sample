package spek

import org.jetbrains.spek.api.dsl.Dsl
import org.jetbrains.spek.api.dsl.Pending
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaPlugins
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers

val rxJavaSchedulersHook = object : RxJavaSchedulersHook() {
    override fun getIOScheduler(): Scheduler = Schedulers.immediate()
    override fun getComputationScheduler(): Scheduler = Schedulers.immediate()
    override fun getNewThreadScheduler(): Scheduler = Schedulers.immediate()
}

val rxAndroidSchedulerHook = object : RxAndroidSchedulersHook() {
    override fun getMainThreadScheduler(): Scheduler = Schedulers.immediate()
}

inline fun Dsl.rxGroup(description: String, pending: Pending = Pending.No,
                       crossinline body: Dsl.() -> Unit) {

    group(description, pending) {
        beforeEach {
            RxJavaPlugins.getInstance().reset()
            RxJavaPlugins.getInstance().registerSchedulersHook(rxJavaSchedulersHook)

            RxAndroidPlugins.getInstance().reset()
            RxAndroidPlugins.getInstance().registerSchedulersHook(rxAndroidSchedulerHook)
        }

        body()

        afterEach {
            RxJavaPlugins.getInstance().reset()

            RxAndroidPlugins.getInstance().reset()
        }
    }
}

inline fun Dsl.describeRx(description: String, crossinline body: Dsl.() -> Unit) =
        rxGroup("describe $description", body = body)

inline fun Dsl.contextRx(description: String, crossinline body: Dsl.() -> Unit) =
        rxGroup("context $description", body = body)

inline fun Dsl.givenRx(description: String, crossinline body: Dsl.() -> Unit) =
        rxGroup("given $description", body = body)

inline fun Dsl.onRx(description: String, crossinline body: Dsl.() -> Unit) =
        rxGroup("on $description", body = body)

inline fun Dsl.xdescribeRx(description: String, reason: String? = null, crossinline body: Dsl.() -> Unit) = rxGroup("describe $description", Pending.Yes(reason), body)

inline fun Dsl.xcontextRx(description: String, reason: String? = null, crossinline body: Dsl.() -> Unit) = rxGroup("context $description", Pending.Yes(reason), body)

inline fun Dsl.xgivenRx(description: String, reason: String? = null, crossinline body: Dsl.() -> Unit) = rxGroup("given $description", Pending.Yes(reason), body)

inline fun Dsl.xonRx(description: String,reason: String? = null,
                     crossinline body: Dsl.() -> Unit) = rxGroup("on $description", Pending.Yes(reason), body)