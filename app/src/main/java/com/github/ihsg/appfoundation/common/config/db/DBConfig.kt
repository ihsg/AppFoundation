package com.github.ihsg.appfoundation.common.config.db

import androidx.room.Room
import com.github.ihsg.appfoundation.App

internal object DBConfig {
    private const val NAME: String = "test.db"
    internal const val VERSION: Int = 1

    fun getDBInstance() = Room.databaseBuilder(App.context, AppDatabase::class.java, NAME)
        .build()
}