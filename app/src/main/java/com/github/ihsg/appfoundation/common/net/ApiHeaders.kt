package com.github.ihsg.appfoundation.common.net

interface ApiHeaders {
    fun buildRequestHeaders(): ArrayList<ApiHeaderBean>
    fun buildRspHeaders(): ArrayList<ApiHeaderBean>
}