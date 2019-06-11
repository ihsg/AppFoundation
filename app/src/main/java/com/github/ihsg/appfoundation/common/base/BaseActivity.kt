package com.github.ihsg.appfoundation.common.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.ihsg.appfoundation.common.widget.LoadingView

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), BaseViewContract {
    private val loadingView: LoadingView by lazy {
        LoadingView(this)
    }

    override fun showLoadingView(message: String) {
        this.loadingView.show()
    }

    override fun hideLoadingView() {
        this.loadingView.dismiss()
    }
}