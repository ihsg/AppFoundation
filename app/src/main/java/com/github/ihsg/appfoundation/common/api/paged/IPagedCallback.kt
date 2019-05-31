package com.github.ihsg.appfoundation.common.api.paged

interface IPagedCallback<T> {
    fun callback(t: T)
}