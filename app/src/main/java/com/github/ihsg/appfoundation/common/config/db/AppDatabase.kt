package com.github.ihsg.appfoundation.common.config.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ihsg.appfoundation.banner.BannerDao
import com.github.ihsg.appfoundation.banner.BannerEntity
import com.github.ihsg.appfoundation.paging.db.StudentDao
import com.github.ihsg.appfoundation.paging.db.StudentEntity

@Database(entities = [BannerEntity::class, StudentEntity::class], version = DBConfig.VERSION, exportSchema = false)
@TypeConverters(DBTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao
    abstract fun studentDao(): StudentDao
}