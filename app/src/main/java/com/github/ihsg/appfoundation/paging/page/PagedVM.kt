package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.api.PagedBean
import com.github.ihsg.appfoundation.common.net.ApiWorker

class PagedVM : ViewModel() {
    private val api: LoanApi by lazy {
        ApiWorker.instance.getApi(LoanApi::class.java)
    }

    fun getLoanList(): LiveData<PagedList<LoanEntity>> {
        val ds = object : DataSource.Factory<PagedBean, LoanEntity>() {
            override fun create(): DataSource<PagedBean, LoanEntity> {
                return PagedDataSource(api)
            }
        }
        return LivePagedListBuilder(
            ds, PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .build()
        ).build()
    }
}