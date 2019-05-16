package com.github.ihsg.appfoundation.common.config

import com.github.ihsg.appfoundation.common.config.db.AppDatabase
import com.github.ihsg.appfoundation.common.config.db.DBConfig
import com.github.ihsg.appfoundation.common.config.net.NetConfigImpl
import com.github.ihsg.appfoundation.common.net.NetConfig

internal object AppConfig {
    fun isDevMode(): Boolean = true
    fun getNetConfig(): NetConfig = NetConfigImpl
    fun getDBInstance(): AppDatabase = DBConfig.getDBInstance()
}