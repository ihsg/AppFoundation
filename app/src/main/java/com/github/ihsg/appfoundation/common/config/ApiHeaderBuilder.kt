package com.github.ihsg.appfoundation.common.config

import com.github.ihsg.appfoundation.common.network.ApiHeaderBean
import com.github.ihsg.appfoundation.common.network.ApiHeaders
import com.github.ihsg.appfoundation.common.util.SysUtil

internal object ApiHeaderBuilder : ApiHeaders {
    private val reqHeaders: ArrayList<ApiHeaderBean> = ArrayList()
    private val rspHeaders: ArrayList<ApiHeaderBean> = ArrayList()

    const val STS: String = "X-TIMESTAMP"

    override fun getReqHeaders(): List<ApiHeaderBean> {
        reqHeaders.clear()
        reqHeaders.add(getReqUserAgentHeader())
        return reqHeaders
    }

    override fun getRspHeaders(): List<ApiHeaderBean> {
        if (rspHeaders.isEmpty()) {
            rspHeaders.add(ApiHeaderBean(STS, ""))
        }
        return rspHeaders
    }

    private fun getReqUserAgentHeader(): ApiHeaderBean {
        return ApiHeaderBean("User-Agent", "Version/${SysUtil.getAppVersion()} ${SysUtil.getDeviceInfo()}")
    }
}