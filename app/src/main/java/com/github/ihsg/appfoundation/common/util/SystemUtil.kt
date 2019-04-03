package com.github.ihsg.appfoundation.common.util

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.github.ihsg.appfoundation.App

object SystemUtil {
    fun getContext(): Context {
        return App.context
    }

    @ColorInt
    fun getColor(@ColorRes colorResId: Int): Int {
        return ContextCompat.getColor(getContext(), colorResId)
    }

    @ColorInt
    fun getColor(colorStr: String): Int {
        return Color.parseColor(colorStr)
    }

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().resources.displayMetrics)
    }
}
