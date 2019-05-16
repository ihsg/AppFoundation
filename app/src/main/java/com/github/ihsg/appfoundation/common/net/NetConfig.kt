package com.github.ihsg.appfoundation.common.net

interface NetConfig {
    val isDevMode: Boolean
    val connectTimeout: Long
    val readTimeout: Long
    val writeTimeout: Long
    val baseUrl: BaseUrl
    val apiHeaders: ApiHeaders
}