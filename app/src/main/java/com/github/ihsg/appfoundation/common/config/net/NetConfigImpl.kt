package com.github.ihsg.appfoundation.common.config.net

import com.github.ihsg.appfoundation.common.config.AppConfig
import com.github.ihsg.appfoundation.common.net.ApiHeaders
import com.github.ihsg.appfoundation.common.net.BaseUrl
import com.github.ihsg.appfoundation.common.net.NetConfig

internal object NetConfigImpl : NetConfig {
    private const val API_DEFAULT_TIMEOUT: Long = 25L

    override val isDevMode: Boolean = AppConfig.isDevMode()

    override val connectTimeout: Long = API_DEFAULT_TIMEOUT

    override val readTimeout: Long = API_DEFAULT_TIMEOUT

    override val writeTimeout: Long = API_DEFAULT_TIMEOUT

    override val baseUrl: BaseUrl = if (!this.isDevMode) DevBaseUrl else ProductBaseUrl

    override val apiHeaders: ApiHeaders = ApiHeadersImpl
}
