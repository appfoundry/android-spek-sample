package be.appfoundry.spekdemo.core.mvp

import rx.Observable
import rx.Single
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class MVPRxPresenter<V : MVPContract.View> : MVPContract.Presenter<V> {
    override var view: V? = null
    private var compositeSubscription = CompositeSubscription()


    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        this.compositeSubscription.unsubscribe()
        this.compositeSubscription = CompositeSubscription()
    }

    protected fun <T> add(observable: Observable<T>, onNext: (t: T) -> Unit, onError: (error: Throwable) -> Unit, onComplete: (() -> Unit)? = null, unsubscribeAutomatically: Boolean = true): Subscription {

        val sub = if (onComplete == null) {
            observable.subscribe(onNext, onError)
        } else {
            observable.subscribe(onNext, onError, onComplete)
        }

        if (unsubscribeAutomatically) {
            compositeSubscription.add(sub)
        }

        return sub
    }

    protected fun <T> add(single: Single<T>, onSuccess: (t: T) -> Unit, onFailure: (error: Throwable) -> Unit): Subscription {
        val sub = single.subscribe(onSuccess, onFailure)
        compositeSubscription.add(sub)
        return sub
    }
}