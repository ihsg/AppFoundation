package com.github.ihsg.appfoundation.common.config.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ihsg.appfoundation.banner.BannerDao
import com.github.ihsg.appfoundation.banner.BannerEntity

@Database(entities = [BannerEntity::class], version = DBConfig.VERSION, exportSchema = false)
//@TypeConverters(DBTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao
}