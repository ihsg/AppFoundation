package com.github.ihsg.appfoundation.common.api

// list api response bean
data class ListBean<T>(val total: Int = 0, val count: Int = 0, val items: List<T>?)