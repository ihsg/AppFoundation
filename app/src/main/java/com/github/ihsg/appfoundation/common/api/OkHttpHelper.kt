package com.github.ihsg.appfoundation.common.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal object OkHttpHelper {
    fun getOkHttpClient(configI: IApiConfig): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(configI.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(configI.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(configI.writeTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)

        // add logger interceptor
        builder.addInterceptor(ApiLoggerInterceptor.create(configI.isDevMode))

        // add parameter interceptor
        builder.addInterceptor(ApiParamsInterceptor.create(configI.apiHeaders))

        return builder.build()
    }
}
