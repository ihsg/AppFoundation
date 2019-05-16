package com.github.ihsg.appfoundation.common.net

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

internal object LoggingInterceptor {
    fun create(isDevMode: Boolean = false): Interceptor {
        val level = if (isDevMode) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }
}