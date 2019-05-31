package com.github.ihsg.appfoundation.common.exts

import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd"
private const val TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

fun Date.toDate(): String {
    return SimpleDateFormat(DATE_FORMAT, Locale.CHINESE).format(this)
}

fun Date.toDateTime(): String {
    return SimpleDateFormat(TIME_FORMAT, Locale.CHINESE).format(this)
}