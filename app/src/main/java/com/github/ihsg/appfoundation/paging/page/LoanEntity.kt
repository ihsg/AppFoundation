package com.github.ihsg.appfoundation.paging.page

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ihsg.appfoundation.common.config.db.DBTableNames
import java.math.BigDecimal
import java.util.*

@Entity(tableName = DBTableNames.T_LOAN)
data class LoanEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val loanId: Int,
    val name: String,
    val amount: BigDecimal,
    val createTime: Date
)