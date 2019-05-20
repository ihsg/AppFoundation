package com.github.ihsg.appfoundation.common.config.db

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*


class DBTypeConverters {
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): Double? {
        return value?.toDouble()
    }

    @TypeConverter
    fun toBigDecimal(value: Double?): BigDecimal? {
        return value?.toBigDecimal()
    }
}