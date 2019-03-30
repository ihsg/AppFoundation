package com.github.ihsg.appfoundation.common.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.github.ihsg.appfoundation.R


class LoadingView(val context: Context) {
    private var view: LoadingDialog? = null

    internal class LoadingDialog(context: Context) : Dialog(context) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.setContentView(R.layout.sub_loading_dialog)
            this.setCanceledOnTouchOutside(false)
        }
    }

    @Synchronized
    fun show() {
        if (this.view == null) {
            this.view = LoadingDialog(context)
        }
        this.view?.show()
    }

    @Synchronized
    fun dismiss() {
        this.view?.dismiss()
        this.view = null
    }
}