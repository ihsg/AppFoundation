package com.github.ihsg.appfoundation.common.config.net

import android.util.ArrayMap
import com.github.ihsg.appfoundation.common.api.bean.ApiHeaderBean
import com.github.ihsg.appfoundation.common.api.IApiHeaders
import com.github.ihsg.appfoundation.common.util.SysUtil
import kotlin.collections.ArrayList

internal object ApiHeadersImpl : IApiHeaders {
    private val reqHeaders = ArrayList<ApiHeaderBean>()
    private val rspHeadersMap = ArrayMap<String, ArrayList<ApiHeaderBean>>()
    private const val STS: String = "X-TIMESTAMP"
    private val userAgentHeader: ApiHeaderBean by lazy { this.getReqUserAgentHeader() }
    private val closedHeader by lazy {
        ApiHeaderBean("Connection", "close")
    }
    private val stsHeader by lazy { ApiHeaderBean(STS, "") }

    override fun buildRequestHeaders(): ArrayList<ApiHeaderBean> {
        this.reqHeaders.clear()
        this.reqHeaders.add(userAgentHeader)
        this.reqHeaders.add(closedHeader)
        return this.reqHeaders
    }

    override fun buildRspHeaders(host: String): ArrayList<ApiHeaderBean> {
        var headers = rspHeadersMap[host]
        if (headers == null) {
            headers = ArrayList()
        }
        if (headers.isEmpty()) {
            headers.add(stsHeader)
        }
        return headers
    }

    private fun getReqUserAgentHeader(): ApiHeaderBean =
            ApiHeaderBean("User-Agent", "Version/${SysUtil.getAppVersion()} ${SysUtil.getDeviceInfo()}")
}