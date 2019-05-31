package com.github.ihsg.appfoundation.paging.page

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LoanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loans: List<LoanEntity>)
}