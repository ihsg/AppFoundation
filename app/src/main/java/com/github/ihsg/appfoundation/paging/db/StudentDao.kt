package com.github.ihsg.appfoundation.paging.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.ihsg.appfoundation.common.config.db.DBTableNames

@Dao
interface StudentDao {
    @Query("SELECT * FROM ${DBTableNames.T_STUDENT} ORDER BY name COLLATE NOCASE ASC")
    fun getAllStudent(): DataSource.Factory<Int, StudentEntity>

    @Insert
    fun insertAll(students: List<StudentEntity>)
}