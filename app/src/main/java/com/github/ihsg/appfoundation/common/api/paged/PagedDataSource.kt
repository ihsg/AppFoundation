package com.github.ihsg.appfoundation.common.api.paged

import androidx.paging.PageKeyedDataSource
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean

class PagedDataSource<T>(private val pagedCallbackWrapper: IPagedCallbackWrapper<PagedRspBean<T>>) :
    PageKeyedDataSource<PagedReqBean, T>() {

    private val curPagedReqBean: PagedReqBean = PagedReqBean(0, 10)
    private val nextPagedReqBean: PagedReqBean = PagedReqBean(0, 10)

    override fun loadInitial(params: LoadInitialParams<PagedReqBean>, callback: LoadInitialCallback<PagedReqBean, T>) {
        this.pagedWorker(0, params.requestedLoadSize) { t: PagedRspBean<T> ->
            t.items?.let {
                callback.onResult(it, curPagedReqBean, nextPagedReqBean)
            }
        }
    }

    override fun loadAfter(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, T>) {
        this.pagedWorker(params.key.offset, params.requestedLoadSize) { t: PagedRspBean<T> ->
            t.items?.let {
                callback.onResult(it, nextPagedReqBean)
            }
        }
    }

    override fun loadBefore(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, T>) {
        // nothing to do...
    }


    private fun pagedWorker(offset: Int, limit: Int, callback: ((t: PagedRspBean<T>) -> Unit)) {
        //1. current paged
        this.curPagedReqBean.offset = offset
        this.curPagedReqBean.limit = limit

        //2. callback on the paged result returned
        val pagedCallback = object : IPagedCallback<PagedRspBean<T>> {
            override fun callback(t: PagedRspBean<T>) {
                // next paged
                if (offset == 0) {
                    nextPagedReqBean.offset = Math.min(limit, t.count)
                } else {
                    nextPagedReqBean.offset += Math.min(limit, t.count)
                }
                nextPagedReqBean.limit = limit

                // update paged
                callback(t)
            }
        }

        //3. callback
        this.pagedCallbackWrapper.callback(this.curPagedReqBean, pagedCallback)
    }
}