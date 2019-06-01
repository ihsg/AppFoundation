package com.github.ihsg.appfoundation.common.api.paged

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.api.LoadState
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.util.LogUtil

abstract class PagedRepository<T> {

    protected val initialState = MutableLiveData<LoadState>()
    protected val refreshState = MutableLiveData<LoadState>()
    protected val moreState = MutableLiveData<LoadState>()
    protected var retry: (() -> Unit)? = null
    protected var loadState: MutableLiveData<LoadState>? = null

    private fun retryAllFailed() {
        val preRetry = this.retry
        this.retry = null
        preRetry?.invoke()
    }

    fun buildPagedBean(): PagedBean<T> {
        val pagedCallWrapper = object : IPagedCallbackWrapper<PagedRspBean<T>> {
            override fun callback(pagedReqBean: PagedReqBean, pagedCallback: IPagedCallback<PagedRspBean<T>>) {
                //1. just return if loading is existed
                if (loadState?.value == LoadState.LOADING) {
                    LogUtil.e("loading...")
                    return
                }

                //2. update state if needed
                loadState = if (pagedReqBean.offset == 0) {
                    if (loadState == null) {
                        initialState
                    } else {
                        refreshState
                    }
                } else {
                    moreState
                }

                //3. api call
                apiWorker(pagedReqBean, loadState, pagedCallback)
            }
        }
        val sourceFactory = PagedDataSourceFactory(pagedCallWrapper)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder()
                .setPageSize(50)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(50)
                .setInitialLoadSizeHint(100)
                .build()
        ).build()

        return PagedBean<T>(
            pagedList = livePagedList,
            initialState = this.initialState,
            refreshState = this.refreshState,
            moreState = this.moreState,
            refresh = {
                loadState = refreshState
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            retry = { this.retryAllFailed() }
        )
    }

    protected abstract fun apiWorker(
        pagedReqBean: PagedReqBean,
        loadState: MutableLiveData<LoadState>?,
        pagedCallback: IPagedCallback<PagedRspBean<T>>
    )
}