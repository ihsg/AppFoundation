package com.github.ihsg.appfoundation.common.exts

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.scheduleIoMain(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.scheduleIo(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())

fun <T> Observable<T>.scheduleMain(): Observable<T> =
    this.observeOn(AndroidSchedulers.mainThread())