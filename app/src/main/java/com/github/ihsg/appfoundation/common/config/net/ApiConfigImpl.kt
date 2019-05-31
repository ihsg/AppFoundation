package com.github.ihsg.appfoundation.common.config.net

import com.github.ihsg.appfoundation.common.config.AppConfig
import com.github.ihsg.appfoundation.common.api.IApiHeaders
import com.github.ihsg.appfoundation.common.api.IApiConfig

internal object ApiConfigImpl : IApiConfig {
    private const val TIMEOUT: Long = 25L //unit: seconds

    override val isDevMode: Boolean = AppConfig.isDevMode()

    override val connectTimeout: Long = TIMEOUT

    override val readTimeout: Long = TIMEOUT

    override val writeTimeout: Long = TIMEOUT

    override val apiBaseUrl: String = if (!this.isDevMode) DevBaseUrlImpl.apiBaseUrl else ProductBaseUrlImpl.apiBaseUrl

    override val apiHeaders: IApiHeaders = ApiHeadersImpl
}
