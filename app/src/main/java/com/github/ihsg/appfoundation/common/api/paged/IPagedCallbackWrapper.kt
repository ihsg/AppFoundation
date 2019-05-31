package com.github.ihsg.appfoundation.common.api.paged

import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean

interface IPagedCallbackWrapper<T> {
    fun callback(pagedReqBean: PagedReqBean, pagedCallback: IPagedCallback<T>)
}