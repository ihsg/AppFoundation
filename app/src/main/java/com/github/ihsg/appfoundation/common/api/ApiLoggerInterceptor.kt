package com.github.ihsg.appfoundation.common.api

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

internal object ApiLoggerInterceptor {
    fun create(isDevMode: Boolean = false): Interceptor {
        val level = if (isDevMode) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }
}