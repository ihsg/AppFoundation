package com.github.ihsg.appfoundation.common.api.bean

// paged api response bean
data class PagedRspBean<T>(val total: Int = 0, val count: Int = 0, val items: List<T>?)