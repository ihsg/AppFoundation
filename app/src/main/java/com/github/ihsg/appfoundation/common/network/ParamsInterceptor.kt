package com.github.ihsg.appfoundation.common.network

import okhttp3.Interceptor

internal object ParamsInterceptor {
    fun create(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            val newBuilder = request.newBuilder()
            val userAgent = ""
            newBuilder.addHeader("User-Agent", userAgent)

            newBuilder.build()

            val response = chain.proceed(request)
            response.headers().get("df")
            response
        }
    }
}