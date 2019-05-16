package com.github.ihsg.appfoundation.banner

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ihsg.appfoundation.common.api.CodeNameBean
import com.github.ihsg.appfoundation.common.config.db.DBTableNames

@Entity(tableName = "${DBTableNames.T_BANNER}")
data class BannerEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var bannerId: String,
    val imageUrl: String?,
    val description: String?,
    var link: String?,
    var linkV2: String?,
    var linkV3: String?,
    var title: String?,
    @Embedded
    var type: CodeNameBean?,
    var isNeedLogin: String?
)