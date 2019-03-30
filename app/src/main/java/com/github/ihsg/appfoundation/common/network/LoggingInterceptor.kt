package com.github.ihsg.appfoundation.common.network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor


object LoggingInterceptor {
    fun create(isDeveloping: Boolean = false): Interceptor {
        val level = if (isDeveloping) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }
}