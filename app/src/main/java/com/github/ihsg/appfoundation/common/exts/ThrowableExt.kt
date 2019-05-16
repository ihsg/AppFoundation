package com.github.ihsg.appfoundation.common.exts

import android.widget.Toast
import com.github.ihsg.appfoundation.App
import com.github.ihsg.appfoundation.common.config.AppConfig
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.handle(isShow: Boolean = true) {
    val errMsg = when (this) {
        is UnknownHostException -> "网络错误：无法解析服务器地址，请稍后重试。"
        is ConnectException -> "网络错误：无法连接到服务器，请稍后重试。"
        is SocketTimeoutException -> "网络错误：连接服务器超时，请稍后重试。"
        is HttpException -> "自定义错误" //TODO: add handler by designing with API
        else -> "未知错误，请稍后重试。"
    }

    if (isShow) {
        Toast.makeText(App.context, errMsg, Toast.LENGTH_LONG).show()
    }

    if (AppConfig.isDevMode()) {
        this.printStackTrace()
    }
}