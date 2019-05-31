package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.LoadState


class LoanListDataSourceFactory(private val api: LoanApi, private val loadState: MutableLiveData<LoadState>) :
    DataSource.Factory<PagedReqBean, LoanEntity>() {
    val sourceLiveData = MutableLiveData<LoanListDataSource>()
    override fun create(): DataSource<PagedReqBean, LoanEntity> {
        val source = LoanListDataSource(api, loadState)
        sourceLiveData.postValue(source)
        return source
    }
}