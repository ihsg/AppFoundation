package com.github.ihsg.appfoundation.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.config.AppConfig
import com.github.ihsg.appfoundation.common.config.db.AppDatabase
import com.github.ihsg.appfoundation.common.exts.scheduleIoMain
import com.github.ihsg.appfoundation.common.api.ApiWorker
import com.github.ihsg.appfoundation.common.api.LoadState
import com.github.ihsg.appfoundation.common.util.LogUtil

class BannerVM : ViewModel() {
    private var bannersLiveData: MutableLiveData<List<BannerEntity>>? = null
    private val api: BannerApi by lazy {
        ApiWorker.getApi(BannerApi::class.java)
    }
    private val db: AppDatabase by lazy {
        AppConfig.getDBInstance()
    }

    val networkState = MutableLiveData<LoadState>()

    fun load(): MutableLiveData<List<BannerEntity>>? {
        this.bannersLiveData = MutableLiveData()
        this.api.getBannerList()
            .map { it ->
                it.items?.let {
                    db.runInTransaction {
                        db.bannerDao().insertAll(it)
                        val d = db.bannerDao().loadAllByType("HOME")
                        LogUtil.d("database: d = $d")
                    }
                }
                it
            }
            .scheduleIoMain()
            .subscribe(
                object : ApiObserver<PagedRspBean<BannerEntity>>(this.networkState) {
                    override fun onNext(t: PagedRspBean<BannerEntity>) {
                        bannersLiveData?.let {
                            it.value = t.items
                        }
                    }
                })
        return this.bannersLiveData
    }
}