package com.github.ihsg.appfoundation.paging.page

import androidx.paging.PageKeyedDataSource
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.ListBean
import com.github.ihsg.appfoundation.common.api.PagedBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PagedDataSource(private val api: LoanApi) :
    PageKeyedDataSource<PagedBean, LoanEntity>() {

    private val curPagedBean: PagedBean = PagedBean(0, 10)
    private val afterPagedBean: PagedBean = PagedBean(0, 10)

    override fun loadInitial(
        params: LoadInitialParams<PagedBean>,
        callback: LoadInitialCallback<PagedBean, LoanEntity>
    ) {
        curPagedBean.limit = params.requestedLoadSize
        curPagedBean.offset = 0

        api.getLoanListApi(curPagedBean.offset, curPagedBean.limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<ListBean<LoanEntity>>() {
                override fun onNext(t: ListBean<LoanEntity>) {
                    afterPagedBean.offset = Math.min(params.requestedLoadSize, t.count)
                    t.items?.let {
                        callback.onResult(it, curPagedBean, afterPagedBean)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<PagedBean>, callback: LoadCallback<PagedBean, LoanEntity>) {
        api.getLoanListApi(params.key.offset, params.key.limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<ListBean<LoanEntity>>() {
                override fun onNext(t: ListBean<LoanEntity>) {
                    t.items?.let {
                        afterPagedBean.offset += Math.min(params.requestedLoadSize, t.count)
                        callback.onResult(it, afterPagedBean)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<PagedBean>, callback: LoadCallback<PagedBean, LoanEntity>) {
    }
}