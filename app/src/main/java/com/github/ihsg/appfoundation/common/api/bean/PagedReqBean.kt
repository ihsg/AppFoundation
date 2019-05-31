package com.github.ihsg.appfoundation.common.api.bean

data class PagedReqBean(var offset: Int, var limit: Int, var total: Boolean = true)