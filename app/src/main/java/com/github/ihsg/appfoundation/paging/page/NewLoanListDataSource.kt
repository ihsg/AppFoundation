package com.github.ihsg.appfoundation.paging.page

import androidx.paging.PageKeyedDataSource
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.util.LogUtil

class NewLoanListDataSource(
        private val pagedCallbackWrapper: IPagedCallbackWrapper<PagedRspBean<LoanEntity>>) :
        PageKeyedDataSource<PagedReqBean, LoanEntity>() {

    private val curPagedReqBean: PagedReqBean = PagedReqBean(0, 10)
    private val nextPagedReqBean: PagedReqBean = PagedReqBean(0, 10)

    override fun loadInitial(
            params: LoadInitialParams<PagedReqBean>,
            callback: LoadInitialCallback<PagedReqBean, LoanEntity>
    ) {
        // current paged
        this.curPagedReqBean.offset = 0
        this.curPagedReqBean.limit = params.requestedLoadSize

        // callback on the paged result returned
        val pagedCallback = object : IPagedCallback<PagedRspBean<LoanEntity>> {
            override fun callback(t: PagedRspBean<LoanEntity>) {
                // next paged
                nextPagedReqBean.offset = Math.min(params.requestedLoadSize, t.count)
                nextPagedReqBean.limit = params.requestedLoadSize

                // update paged
                t.items?.let {
                    callback.onResult(it, curPagedReqBean, nextPagedReqBean)
                }
            }
        }

        this.pagedCallbackWrapper.callback(this.curPagedReqBean, pagedCallback)
    }

    override fun loadAfter(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, LoanEntity>) {
        // current paged
        this.curPagedReqBean.limit = params.requestedLoadSize
        this.curPagedReqBean.offset = params.key.offset

        // callback on the paged result returned
        val pagedCallback = object : IPagedCallback<PagedRspBean<LoanEntity>> {
            override fun callback(t: PagedRspBean<LoanEntity>) {
                // next paged
                nextPagedReqBean.offset = Math.min(params.requestedLoadSize, t.count)
                nextPagedReqBean.limit = params.requestedLoadSize

                // update paged
                t.items?.let {
                    callback.onResult(it, nextPagedReqBean)
                }
            }
        }

        this.pagedCallbackWrapper.callback(this.curPagedReqBean, pagedCallback)
    }

    override fun loadBefore(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, LoanEntity>) {
        LogUtil.i("loadBefore---")
    }
}