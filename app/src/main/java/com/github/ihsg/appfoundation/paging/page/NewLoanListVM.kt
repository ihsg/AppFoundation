package com.github.ihsg.appfoundation.paging.page

import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.api.paged.PagedBean

class NewLoanListVM : ViewModel() {
    private val pagedBean: PagedBean<LoanEntity> = LoanRepository().buildPagedBean()

    val loans = pagedBean.pagedList
    val initialState = pagedBean.initialState
    val refreshState = pagedBean.refreshState
    val moreState = pagedBean.moreState

    fun refresh() {
        this.pagedBean.refresh.invoke()
    }

    fun retry() {
        this.pagedBean.retry.invoke()
    }
}