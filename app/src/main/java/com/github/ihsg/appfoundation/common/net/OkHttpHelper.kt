package com.github.ihsg.appfoundation.common.net

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal object OkHttpHelper {
    fun getOkHttpClient(config: NetConfig): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(config.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(config.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)

        // add logger interceptor
        builder.addInterceptor(LoggingInterceptor.create(config.isDevMode))

        // add parameter interceptor
        builder.addInterceptor(ParamsInterceptor.create(config.apiHeaders))

        return builder.build()
    }
}
