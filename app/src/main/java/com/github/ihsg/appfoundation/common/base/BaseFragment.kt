package com.github.ihsg.appfoundation.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseViewContract {
    protected var activityContext: BaseActivity? = null
    private var baseViewContract: BaseViewContract? = null
    protected var contentView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = activity as BaseActivity
        this.baseViewContract = this.activityContext
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.contentView = inflater.inflate(this.getLayoutResId(), container, false)
        return this.contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initialize()
    }

    override fun showLoadingView(message: String) {
        this.baseViewContract?.showLoadingView(message)
    }

    override fun hideLoadingView() {
        this.baseViewContract?.hideLoadingView()
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    open fun initialize() {}
}