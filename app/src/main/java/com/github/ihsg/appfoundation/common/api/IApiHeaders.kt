package com.github.ihsg.appfoundation.common.api

import com.github.ihsg.appfoundation.common.api.bean.ApiHeaderBean

interface IApiHeaders {
    fun buildRequestHeaders(): ArrayList<ApiHeaderBean>
    fun buildRspHeaders(host: String): ArrayList<ApiHeaderBean>
}