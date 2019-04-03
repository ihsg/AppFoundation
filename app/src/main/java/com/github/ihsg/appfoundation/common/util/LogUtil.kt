package com.github.ihsg.appfoundation.common.util

import com.github.ihsg.appfoundation.common.config.AppConfiguration
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

object LogUtil {
    private enum class LoggerLevel {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        ASSERT,
        NONE
    }

    private val loggerLevel = if (AppConfiguration.isDeveloping()) LoggerLevel.VERBOSE else LoggerLevel.ERROR

    fun init() {
        Logger.addLogAdapter(
            AndroidLogAdapter(
                PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)
                    .methodCount(0)
                    .methodOffset(1)
                    .build()
            )
        )
    }

    private fun getLoggable(level: LoggerLevel): Boolean {
        return level >= loggerLevel
    }

    fun v(message: String) {
        if (this.getLoggable(LoggerLevel.VERBOSE)) {
            Logger.v(message)
        }
    }

    fun d(message: String) {
        if (this.getLoggable(LoggerLevel.DEBUG)) {
            Logger.d(message)
        }
    }

    fun i(message: String) {
        if (getLoggable(LoggerLevel.INFO)) {
            Logger.i(message)
        }
    }

    fun w(message: String) {
        if (getLoggable(LoggerLevel.WARN)) {
            Logger.w(message)
        }
    }

    fun e(message: String) {
        if (getLoggable(LoggerLevel.ERROR)) {
            Logger.e(message)
        }
    }

    fun e(throwable: Throwable) {
        if (getLoggable(LoggerLevel.ERROR)) {
            throwable.printStackTrace()
        }
    }

    fun wtf(message: String) {
        if (getLoggable(LoggerLevel.ERROR)) {
            Logger.wtf(message)
        }
    }

    fun json(message: String) {
        if (getLoggable(LoggerLevel.DEBUG)) {
            Logger.json(message)
        }
    }
}