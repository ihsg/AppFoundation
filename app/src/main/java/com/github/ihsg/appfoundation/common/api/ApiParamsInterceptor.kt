package com.github.ihsg.appfoundation.common.api

import com.github.ihsg.appfoundation.common.api.bean.ApiHeaderBean
import okhttp3.Interceptor
import java.util.concurrent.locks.ReentrantLock

internal object ApiParamsInterceptor {
    private val lock = ReentrantLock()
    fun create(apiHeaders: IApiHeaders): Interceptor {
        return Interceptor { chain ->

            // request
            val request = chain.request()
            val newBuilder = request.newBuilder()

            val reqHeaders = apiHeaders.buildRequestHeaders()
            for (header: ApiHeaderBean in reqHeaders) {
                newBuilder.addHeader(header.name, header.value)
            }

            newBuilder.build()

            // response
            val response = chain.proceed(request)

            try {
                lock.lock()
                val rspHeaders = apiHeaders.buildRspHeaders(request.url().host())
                for (header: ApiHeaderBean in rspHeaders) {
                    header.value = response.header(header.name) ?: ""
                }
            } finally {
                lock.unlock()
            }

            response
        }
    }
}