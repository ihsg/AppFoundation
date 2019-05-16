package com.github.ihsg.appfoundation.common.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.github.ihsg.appfoundation.common.widget.LoadingView

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseViewContract {
    private val loadingView: LoadingView by lazy {
        LoadingView(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun showLoadingView(message: String) {
        this.loadingView.show()
    }

    override fun hideLoadingView() {
        this.loadingView.dismiss()
    }
}