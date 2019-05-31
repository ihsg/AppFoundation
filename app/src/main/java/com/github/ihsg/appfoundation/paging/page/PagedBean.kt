package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.api.LoadState

data class PagedBean<T>(
        val pagedList: LiveData<PagedList<T>>,
        val initialState: LiveData<LoadState>,
        val refreshState: LiveData<LoadState>,
        val moreState: LiveData<LoadState>,
        val refresh: () -> Unit,
        val retry: () -> Unit
)