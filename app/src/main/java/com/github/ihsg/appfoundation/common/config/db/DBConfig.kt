package com.github.ihsg.appfoundation.common.config.db

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.ihsg.appfoundation.App

internal object DBConfig {
    private const val NAME: String = "test.db"
    internal const val VERSION: Int = 2

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS ${DBTableNames.T_STUDENT} (id INTEGER NOT NULL , name TEXT NOT NULL, PRIMARY KEY (id))")
        }
    }

    fun getDBInstance() = Room.databaseBuilder(App.context, AppDatabase::class.java, NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
}