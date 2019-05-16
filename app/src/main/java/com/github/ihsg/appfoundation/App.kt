package com.github.ihsg.appfoundation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.ihsg.appfoundation.common.util.LogUtil
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        LogUtil.init()
        initUMeng()
    }

    private fun initUMeng(){
        val key ="4"
        val channel ="3"
        val deviceType = UMConfigure.DEVICE_TYPE_PHONE
        val pushSecret = ""

        // 初始化SDK
        UMConfigure.init(this, key, channel, deviceType, pushSecret)

        // auto模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }
}