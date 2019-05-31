package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.MutableLiveData
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.ApiWorker
import com.github.ihsg.appfoundation.common.api.LoadState
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.api.paged.IPagedCallback
import com.github.ihsg.appfoundation.common.api.paged.PagedRepository
import com.github.ihsg.appfoundation.common.config.AppConfig
import com.github.ihsg.appfoundation.common.exts.scheduleIoMain
import com.github.ihsg.appfoundation.common.exts.toQueryMap

class LoanRepository : PagedRepository<LoanEntity>() {
    private val api: LoanApi by lazy {
        ApiWorker.getApi(LoanApi::class.java)
    }

    private val db: LoanDao by lazy {
        AppConfig.getDBInstance().loanDao()
    }

    override fun apiWorker(pagedReqBean: PagedReqBean, loadState: MutableLiveData<LoadState>?, pagedCallback: IPagedCallback<PagedRspBean<LoanEntity>>) {
        api.getLoanListApi(pagedReqBean.toQueryMap())
                .map { it ->
                    it.items?.let {
                        db.insert(it)
                    }
                    it
                }
                .scheduleIoMain()
                .subscribe(object : ApiObserver<PagedRspBean<LoanEntity>>(loadState) {
                    override fun onNext(t: PagedRspBean<LoanEntity>) {
                        pagedCallback.callback(t)
                        retry = null
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        retry = {
                            apiWorker(pagedReqBean, loadState, pagedCallback)
                        }
                    }
                })
    }
}