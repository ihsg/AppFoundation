package com.github.ihsg.appfoundation.common.api

import com.github.ihsg.appfoundation.common.config.AppConfig

object ApiWorker {
    private var IApiConfig: IApiConfig = AppConfig.getApiConfig()
    private var okHttpClient = OkHttpHelper.getOkHttpClient(IApiConfig)
    private var retrofit = RetrofitHelper.getRetrofit(IApiConfig.apiBaseUrl, okHttpClient)

    fun <T> getApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    fun <T> getApi(apiClass: Class<T>, baseUrl: String): T {
        val retrofit = RetrofitHelper.getRetrofit(baseUrl, okHttpClient)
        return retrofit.create(apiClass)
    }

    fun <T> getApi(apiClass: Class<T>, IApiConfig: IApiConfig): T {
        val okHttpClient = OkHttpHelper.getOkHttpClient(IApiConfig)
        val retrofit = RetrofitHelper.getRetrofit(IApiConfig.apiBaseUrl, okHttpClient)
        return retrofit.create(apiClass)
    }
}
