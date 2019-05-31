package com.github.ihsg.appfoundation.common.api.paged

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean
import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean

class PagedDataSourceFactory<T>(private val pagedCallbackWrapper: IPagedCallbackWrapper<PagedRspBean<T>>)
    : DataSource.Factory<PagedReqBean, T>() {
    val sourceLiveData = MutableLiveData<PagedDataSource<T>>()
    override fun create(): DataSource<PagedReqBean, T> {
        val source = PagedDataSource(this.pagedCallbackWrapper)
        sourceLiveData.postValue(source)
        return source
    }
}