package com.github.ihsg.appfoundation.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.ListBean
import com.github.ihsg.appfoundation.common.base.BaseViewContract
import com.github.ihsg.appfoundation.common.config.AppConfig
import com.github.ihsg.appfoundation.common.config.db.AppDatabase
import com.github.ihsg.appfoundation.common.exts.scheduleIoMain
import com.github.ihsg.appfoundation.common.net.ApiWorker
import com.github.ihsg.appfoundation.common.util.LogUtil

class BannerVM : ViewModel() {
    private var bannersLiveData: MutableLiveData<List<BannerEntity>>? = null
    private val api: BannerApi by lazy {
        ApiWorker.instance.getApi(BannerApi::class.java)
    }
    private val db: AppDatabase by lazy {
        AppConfig.getDBInstance()
    }

    fun load(viewContract: BaseViewContract): MutableLiveData<List<BannerEntity>>? {
        this.bannersLiveData = MutableLiveData()
        this.api.getBannerList()
            .map { it ->
                it.items?.let {
                    val old = db.bannerDao().loadAll()
                    db.bannerDao().deleteAll(old)
                    db.bannerDao().insertAll(it)
                    val d = db.bannerDao().loadAllByType("HOME")
                    LogUtil.d("database: d = ${d}")
                }
                it
            }
            .scheduleIoMain()
            .subscribe(
                object : ApiObserver<ListBean<BannerEntity>>(viewContract) {
                    override fun onNext(t: ListBean<BannerEntity>) {
                        bannersLiveData?.let {
                            it.value = t.items
                        }
                    }
                })
        return this.bannersLiveData
    }
}