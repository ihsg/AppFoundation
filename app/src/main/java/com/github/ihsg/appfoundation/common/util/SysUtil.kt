package com.github.ihsg.appfoundation.common.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.github.ihsg.appfoundation.App
import com.github.ihsg.appfoundation.BuildConfig

object SysUtil {
    fun getAppContext(): Context {
        return App.context
    }

    @ColorInt
    fun getColor(@ColorRes colorResId: Int): Int {
        return ContextCompat.getColor(getAppContext(), colorResId)
    }

    @ColorInt
    fun getColor(colorStr: String): Int {
        return Color.parseColor(colorStr)
    }

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getAppContext().resources.displayMetrics)
    }

    fun getAppVersion(): String {
        return getAppContext()
                .packageManager
                .getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_META_DATA)
                .versionName
    }

    fun getDeviceInfo(): String {
        return "Product/${Build.PRODUCT} " +
                "SDK/${Build.VERSION.SDK_INT} " +
                "Android/${Build.VERSION.RELEASE} " +
                "Brand/${Build.BRAND} " +
                "Board/${Build.BOARD} " +
                "ID/${Build.ID} " +
                "Manufacturer/${Build.MANUFACTURER}"
    }
}
