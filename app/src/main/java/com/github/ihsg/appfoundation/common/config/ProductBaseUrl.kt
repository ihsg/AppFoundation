package com.github.ihsg.appfoundation.common.config

import com.github.ihsg.appfoundation.common.network.BaseUrl

internal object ProductBaseUrl : BaseUrl {
    override val apiBaseUrl: String = "https://www.lvjinsuo.com/api/"
    override val h5BaseUrl: String = ""
    override val websiteBaseUrl: String = ""
}