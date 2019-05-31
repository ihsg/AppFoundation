package com.github.ihsg.appfoundation.common.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.github.ihsg.appfoundation.common.widget.LoadingView

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), BaseViewContract {
    private val loadingView: LoadingView by lazy {
        LoadingView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(this.getLayoutResId())
        this.initialize()
    }

    override fun showLoadingView(message: String) {
        this.loadingView.show()
    }

    override fun hideLoadingView() {
        this.loadingView.dismiss()
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    open fun initialize() {}
}