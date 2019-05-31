package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean

class NewLoanListDataSourceFactory(private val pagedCallbackWrapper: IPagedCallbackWrapper<PagedRspBean<LoanEntity>>) : DataSource.Factory<PagedReqBean, LoanEntity>() {
    val sourceLiveData = MutableLiveData<NewLoanListDataSource>()
    override fun create(): DataSource<PagedReqBean, LoanEntity> {
        val source = NewLoanListDataSource(this.pagedCallbackWrapper)
        sourceLiveData.postValue(source)
        return source
    }
}