package com.github.ihsg.appfoundation.common.api

import com.github.ihsg.appfoundation.common.base.BaseViewContract
import com.github.ihsg.appfoundation.common.exts.handle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class ApiObserver<T>(
    private val viewContract: BaseViewContract? = null,
    private val isShowLoading: Boolean = true
) : Observer<T> {
    override fun onComplete() {
        if (this.isShowLoading) {
            viewContract?.hideLoadingView()
        }
    }

    override fun onSubscribe(d: Disposable) {
        if (this.isShowLoading) {
            viewContract?.showLoadingView()
        }

    }

    override fun onError(e: Throwable) {
        if (this.isShowLoading) {
            viewContract?.hideLoadingView()
        }
        e.handle()
    }
}