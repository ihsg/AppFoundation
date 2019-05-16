package com.github.ihsg.appfoundation.common.net

import com.github.ihsg.appfoundation.common.config.AppConfig

class ApiWorker private constructor() {
    companion object {
        val instance: ApiWorker by lazy { ApiWorker() }
    }

    private val netConfig: NetConfig = AppConfig.getNetConfig()
    private val okHttpClient = OkHttpHelper.getOkHttpClient(this.netConfig)
    private val retrofit = RetrofitHelper.getRetrofit(this.netConfig, this.okHttpClient)

    fun <T> getApi(apiClass: Class<T>): T {
        return this.retrofit.create(apiClass)
    }
}
