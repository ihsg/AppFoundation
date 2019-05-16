package com.github.ihsg.appfoundation.common.config.net

import com.github.ihsg.appfoundation.common.net.ApiHeaderBean
import com.github.ihsg.appfoundation.common.net.ApiHeaders
import com.github.ihsg.appfoundation.common.util.LogUtil
import com.github.ihsg.appfoundation.common.util.SysUtil

internal object ApiHeadersImpl : ApiHeaders {
    private val reqHeaders = ArrayList<ApiHeaderBean>()
    private val rspHeaders = ArrayList<ApiHeaderBean>()

    private const val STS: String = "X-TIMESTAMP"
    private val userAgentHeader = getReqUserAgentHeader()
    private val stsHeader = ApiHeaderBean(STS, "")

    override fun buildRequestHeaders(): ArrayList<ApiHeaderBean> {
        if (this.rspHeaders.isNotEmpty()) {
            LogUtil.w("ApiHeadersImpl: sts = ${rspHeaders[0]}")
        }
        this.reqHeaders.clear()
        this.reqHeaders.add(userAgentHeader)
        return this.reqHeaders
    }

    override fun buildRspHeaders(): ArrayList<ApiHeaderBean> {
        if (this.rspHeaders.isEmpty()) {
            this.rspHeaders.add(stsHeader)
        }
        return this.rspHeaders
    }

    private fun getReqUserAgentHeader(): ApiHeaderBean =
        ApiHeaderBean("User-Agent", "Version/${SysUtil.getAppVersion()} ${SysUtil.getDeviceInfo()}")

}