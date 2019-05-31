package com.github.ihsg.appfoundation.common.config

import com.github.ihsg.appfoundation.common.config.db.AppDatabase
import com.github.ihsg.appfoundation.common.config.db.DBConfig
import com.github.ihsg.appfoundation.common.config.net.ApiConfigImpl
import com.github.ihsg.appfoundation.common.api.IApiConfig

internal object AppConfig {
    fun isDevMode(): Boolean = true
    fun getApiConfig(): IApiConfig = ApiConfigImpl
    fun getDBInstance(): AppDatabase = DBConfig.getDBInstance()
}