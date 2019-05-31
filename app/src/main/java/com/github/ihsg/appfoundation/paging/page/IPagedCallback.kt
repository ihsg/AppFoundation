package com.github.ihsg.appfoundation.paging.page

interface IPagedCallback<T> {
    fun callback(t: T)
}