package com.github.ihsg.appfoundation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.ihsg.appfoundation.common.util.LogUtil

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        LogUtil.init()
    }
}