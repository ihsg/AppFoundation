package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.api.ApiWorker
import com.github.ihsg.appfoundation.common.api.LoadState

class LoanListVM : ViewModel() {
    private val api: LoanApi by lazy {
        ApiWorker.getApi(LoanApi::class.java)
    }

    private val networkState = MutableLiveData<LoadState>()

    private val dataSourceFactory: LoanListDataSourceFactory by lazy {
        LoanListDataSourceFactory(api, networkState)
    }

    fun getLoanList(): LiveData<PagedList<LoanEntity>> {
        return LivePagedListBuilder(
            this.dataSourceFactory,
            PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .build()
        ).build()
    }

    fun refresh() {
        this.dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}