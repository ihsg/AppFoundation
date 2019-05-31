package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.ApiWorker
import com.github.ihsg.appfoundation.common.api.LoadState
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.exts.scheduleIoMain
import com.github.ihsg.appfoundation.common.exts.toQueryMap
import com.github.ihsg.appfoundation.common.util.LogUtil

class LoanRepository {
    private val api: LoanApi by lazy {
        ApiWorker.getApi(LoanApi::class.java)
    }

    private val initialState = MutableLiveData<LoadState>()
    private val refreshState = MutableLiveData<LoadState>()
    private val moreState = MutableLiveData<LoadState>()
    private var retry: (() -> Unit)? = null
    private var loadState: MutableLiveData<LoadState>? = null

    val pagedBean: PagedBean<LoanEntity> by lazy {
        val pagedCallWrapper = object : IPagedCallbackWrapper<PagedRspBean<LoanEntity>> {
            override fun callback(pagedReqBean: PagedReqBean, pagedCallback: IPagedCallback<PagedRspBean<LoanEntity>>) {
                //1. just return if loading is existed
                if (loadState != null && loadState?.value == LoadState.LOADING) {
                    LogUtil.e("loading...")
                    return
                }

                // 2. update state if needed
                loadState = if (pagedReqBean.offset == 0) {
                    if (loadState == null) {
                        initialState
                    } else {
                        refreshState
                    }
                } else {
                    moreState
                }

                api.getLoanListApi(pagedReqBean.toQueryMap())
                        .scheduleIoMain()
                        .subscribe(object : ApiObserver<PagedRspBean<LoanEntity>>(loadState) {
                            override fun onNext(t: PagedRspBean<LoanEntity>) {
                                pagedCallback.callback(t)
                                retry = null
                            }

                            override fun onError(e: Throwable) {
                                super.onError(e)
                                retry = {
                                    callback(pagedReqBean, pagedCallback)
                                }
                            }
                        })
            }
        }
        val sourceFactory = NewLoanListDataSourceFactory(pagedCallWrapper)

        val livePagedList = LivePagedListBuilder(
                sourceFactory,
                PagedList.Config.Builder()
                        .setPageSize(5)
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .build()
        ).build()

        PagedBean(
                pagedList = livePagedList,
                initialState = this.initialState,
                refreshState = this.refreshState,
                moreState = this.moreState,
                refresh = {
                    loadState = refreshState
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                retry = {
                    retry?.invoke()
                }
        )
    }
}