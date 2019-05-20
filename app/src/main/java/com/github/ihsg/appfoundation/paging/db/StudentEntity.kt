package com.github.ihsg.appfoundation.paging.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ihsg.appfoundation.common.config.db.DBTableNames

@Entity(tableName = DBTableNames.T_STUDENT)
data class StudentEntity(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)