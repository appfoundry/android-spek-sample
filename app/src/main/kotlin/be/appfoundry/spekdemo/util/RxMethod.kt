package be.appfoundry.spekdemo.util

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun doSomeRxing(): Observable<Long> =
        Observable.just(1L).observeOn(AndroidSchedulers.mainThread())


fun doSomeLongRxing(): Observable<Boolean> =
        Observable.defer {
            try {
                Thread.sleep(5000)
            } catch (error: InterruptedException) {
                return@defer Observable.error<Boolean>(error)
            }

            return@defer Observable.just(true)
        }.subscribeOn(Schedulers.newThread())