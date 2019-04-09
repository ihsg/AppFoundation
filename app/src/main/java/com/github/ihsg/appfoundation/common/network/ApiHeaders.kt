package com.github.ihsg.appfoundation.common.network

internal interface ApiHeaders {
    fun getReqHeaders(): List<ApiHeaderBean>
    fun getRspHeaders(): List<ApiHeaderBean>
}