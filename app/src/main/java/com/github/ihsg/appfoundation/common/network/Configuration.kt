package com.github.ihsg.appfoundation.common.network

import okhttp3.Interceptor

internal data class Configuration(
    val connectTimeout: Long = 25,
    val readTimeout: Long = 25,
    val writeTimeout: Long = 25,
    val apiBaseUrl: String = ""
) {
    val interceptors: ArrayList<Interceptor> = ArrayList()

    fun addInterceptor(interceptor: Interceptor) {
        this.interceptors.add(interceptor)
    }
}