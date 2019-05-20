package com.github.ihsg.appfoundation.common.exts

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
@SuppressLint("SimpleDateFormat")
private val TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


fun Date.toDate(): String {
    return DATE_FORMAT.format(this)
}

fun Date.toDateTime(): String {
    return TIME_FORMAT.format(this)
}