package com.github.ihsg.appfoundation.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.ListBean
import com.github.ihsg.appfoundation.common.base.BaseViewContract
import com.github.ihsg.appfoundation.common.exts.scheduleIoMain
import com.github.ihsg.appfoundation.common.network.ApiWorker

class BannerVM : ViewModel() {
    private var bannersLiveData: MutableLiveData<List<BannerBean>>? = null
    private val api: BannerApi by lazy {
        ApiWorker.instance.getApi(BannerApi::class.java)
    }

    fun load(viewContract: BaseViewContract): MutableLiveData<List<BannerBean>>? {
        this.bannersLiveData = MutableLiveData()
        this.api.getBannerList()
            .scheduleIoMain()
            .subscribe(object : ApiObserver<ListBean<BannerBean>>(viewContract) {
                override fun onNext(t: ListBean<BannerBean>) {
                    bannersLiveData?.let {
                        it.value = t.items
                    }
                }
            })
        return this.bannersLiveData
    }
}