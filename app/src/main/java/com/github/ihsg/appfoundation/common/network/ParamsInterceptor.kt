package com.github.ihsg.appfoundation.common.network

import okhttp3.Interceptor

internal object ParamsInterceptor {
    fun create(apiHeaders: ApiHeaders): Interceptor {
        return Interceptor { chain ->
            // request
            val request = chain.request()
            val newBuilder = request.newBuilder()

            val reqHeaders = apiHeaders.getReqHeaders()
            for (header: ApiHeaderBean in reqHeaders) {
                newBuilder.addHeader(header.name, header.value)
            }

            newBuilder.build()

            // response
            val response = chain.proceed(request)

            val rspHeaders = apiHeaders.getRspHeaders()
            for (header: ApiHeaderBean in rspHeaders) {
                header.value = response.header(header.name) ?: ""
            }
            response
        }
    }
}