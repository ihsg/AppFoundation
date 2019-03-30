package com.github.ihsg.appfoundation.common.api

import com.github.ihsg.appfoundation.common.base.BaseViewContract
import com.github.ihsg.appfoundation.common.exts.handle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class ApiObserver<T>(private val viewContract: BaseViewContract) : Observer<T> {
    override fun onComplete() {
        viewContract.hideLoadingView()
    }

    override fun onSubscribe(d: Disposable) {
        viewContract.showLoadingView()
    }

    override fun onError(e: Throwable) {
        e.handle()
    }
}