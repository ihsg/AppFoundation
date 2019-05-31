package com.github.ihsg.appfoundation.banner

import androidx.room.*
import com.github.ihsg.appfoundation.common.config.db.DBTableNames

@Dao
interface BannerDao {
    @Query("SELECT * from ${DBTableNames.T_BANNER}")
    fun loadAll(): List<BannerEntity>

    @Query("SELECT * FROM ${DBTableNames.T_BANNER} WHERE code IN (:type)")
    fun loadAllByType(type: String): List<BannerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(bannerEntities: List<BannerEntity>)

    @Delete
    fun deleteAll(bannerEntities: List<BannerEntity>)

    @Delete
    fun delete(bannerEntity: BannerEntity)
}