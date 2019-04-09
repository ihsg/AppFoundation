package com.github.ihsg.appfoundation.common.config

import com.github.ihsg.appfoundation.common.network.ApiHeaders
import com.github.ihsg.appfoundation.common.network.BaseUrl

internal object AppConfiguration {
    private const val API_DEFAULT_TIMEOUT: Long = 25L

    fun isDeveloping(): Boolean {
        return true
    }

    fun getApiConnectTimeout(): Long {
        return API_DEFAULT_TIMEOUT
    }

    fun getApiReadTimeout(): Long {
        return API_DEFAULT_TIMEOUT
    }

    fun getApiWriteTimeout(): Long {
        return API_DEFAULT_TIMEOUT
    }

    fun getDevBaseUrl(): BaseUrl {
        return DevBaseUrl
    }

    fun getProductBaseUrl(): BaseUrl {
        return ProductBaseUrl
    }

    fun getApiHeaders(): ApiHeaders {
        return ApiHeaderBuilder
    }
}