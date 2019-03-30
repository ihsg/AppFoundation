package com.github.ihsg.appfoundation.common.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal object OkHttpHelper {
    fun getOkHttpClient(configuration: Configuration): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(configuration.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(configuration.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(configuration.writeTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)

        if (configuration.interceptors.isNotEmpty()) {
            configuration.interceptors.forEach { interceptor ->
                builder.addInterceptor(interceptor)
            }
        }

        return builder.build()
    }
}
