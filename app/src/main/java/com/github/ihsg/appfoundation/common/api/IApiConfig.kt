package com.github.ihsg.appfoundation.common.api

interface IApiConfig {
    val isDevMode: Boolean
    val connectTimeout: Long
    val readTimeout: Long
    val writeTimeout: Long
    val apiBaseUrl: String
    val apiHeaders: IApiHeaders
}