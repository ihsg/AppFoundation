package com.github.ihsg.appfoundation.common.api

import androidx.lifecycle.MutableLiveData
import com.github.ihsg.appfoundation.common.exts.handle
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class ApiObserver<T>(private val loadState: MutableLiveData<LoadState>? = null) : Observer<T> {
    override fun onComplete() {
        this.loadState?.postValue(LoadState.LOADED)
    }

    override fun onSubscribe(d: Disposable) {
        this.loadState?.postValue(LoadState.LOADING)
    }

    override fun onError(e: Throwable) {
        val error = e.handle()
        this.loadState?.postValue(LoadState.error(error))
    }
}