package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.ihsg.appfoundation.common.api.ApiObserver
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import com.github.ihsg.appfoundation.common.exts.toQueryMap
import com.github.ihsg.appfoundation.common.api.LoadState
import com.github.ihsg.appfoundation.common.util.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoanListDataSource(private val api: LoanApi, private val loadState: MutableLiveData<LoadState>) :
        PageKeyedDataSource<PagedReqBean, LoanEntity>() {
    private val curPagedReqBean: PagedReqBean =
            PagedReqBean(0, 10)
    private val afterPagedReqBean: PagedReqBean =
            PagedReqBean(0, 10)


    override fun loadInitial(
            params: LoadInitialParams<PagedReqBean>,
            callback: LoadInitialCallback<PagedReqBean, LoanEntity>
    ) {
        curPagedReqBean.limit = params.requestedLoadSize
        curPagedReqBean.offset = 0


        api.getLoanListApi(curPagedReqBean.toQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<PagedRspBean<LoanEntity>>(this.loadState) {
                    override fun onNext(t: PagedRspBean<LoanEntity>) {
                        afterPagedReqBean.offset = Math.min(params.requestedLoadSize, t.count)
                        afterPagedReqBean.limit = params.requestedLoadSize
                        t.items?.let {
                            callback.onResult(it, curPagedReqBean, afterPagedReqBean)
                        }
                    }
                })
    }

    override fun loadAfter(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, LoanEntity>) {
        LogUtil.i("size = ${params.requestedLoadSize}")
        api.getLoanListApi(params.key.toQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<PagedRspBean<LoanEntity>>(this.loadState) {
                    override fun onNext(t: PagedRspBean<LoanEntity>) {
                        t.items?.let {
                            afterPagedReqBean.offset += Math.min(params.requestedLoadSize, t.count)
                            callback.onResult(it, afterPagedReqBean)
                        }
                    }
                })
    }

    override fun loadBefore(params: LoadParams<PagedReqBean>, callback: LoadCallback<PagedReqBean, LoanEntity>) {
        LogUtil.i("loadBefore---")
    }
}