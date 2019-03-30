package com.github.ihsg.appfoundation.common.network

import com.github.ihsg.appfoundation.common.config.AppConfiguration
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiWorker {

    companion object {
        val instance: ApiWorker =
            ApiWorker.SingletonHolder.INSTANCE
    }


    private var configuration: Configuration
    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit
    private var loggingInterceptor: Interceptor
    private var paramsInterceptor: Interceptor
    private var baseUrl: BaseUrl
    private var isDeveloping: Boolean = false

    private object SingletonHolder {
        internal val INSTANCE = ApiWorker()
    }

    init {
        this.isDeveloping = AppConfiguration.isDeveloping()
        loggingInterceptor = getLoggingInterceptor()
        paramsInterceptor =
            getParamsInterceptor()
        baseUrl =
            getBaseUrl()
        configuration =
            getConfiguration()
        okHttpClient =
            getOkHttpClient()
        retrofit =
            getRetrofit()
    }

    fun <T> getApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    private fun getConfiguration(): Configuration {
        val configuration = Configuration(
            AppConfiguration.getApiConnectTimeout(),
            AppConfiguration.getApiReadTimeout(),
            AppConfiguration.getApiWriteTimeout(),
            baseUrl.apiBaseUrl
        )
        configuration.addInterceptor(loggingInterceptor)
        configuration.addInterceptor(paramsInterceptor)
        return configuration
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpHelper.getOkHttpClient(configuration)
    }

    private fun getRetrofit(): Retrofit {
        return RetrofitHelper.getRetrofit(
            configuration,
            okHttpClient
        )
    }

    private fun getLoggingInterceptor(): Interceptor {
        return LoggingInterceptor.create(isDeveloping)
    }

    private fun getParamsInterceptor(): Interceptor {
        return ParamsInterceptor.create()
    }

    private fun getBaseUrl(): BaseUrl {
        return if (isDeveloping) getDevBaseUrl() else getProductBaseUrl()
    }

    private fun getDevBaseUrl(): BaseUrl {
        return AppConfiguration.getDevBaseUrl()
    }

    private fun getProductBaseUrl(): BaseUrl {
        return AppConfiguration.getProductBaseUrl()
    }
}
