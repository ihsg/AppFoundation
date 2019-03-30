package com.github.ihsg.appfoundation.banner

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.api.ListBean
import com.github.ihsg.appfoundation.common.network.ApiWorker
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BannerVM : ViewModel() {
    private var bannersLiveData: MutableLiveData<List<BannerBean>>? = null

    fun load(): MutableLiveData<List<BannerBean>>? {
        this.bannersLiveData = MutableLiveData()
        ApiWorker.instance.getApi(BannerApi::class.java)
            .getBannerList()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ListBean<BannerBean>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ListBean<BannerBean>) {
                    bannersLiveData?.let {
                        it.value = t.items
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("", "", e)
                }
            })
        return this.bannersLiveData
    }
}