package com.github.ihsg.appfoundation.common.base

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), BaseViewContract {
    protected var activityContext: BaseActivity? = null
    private var baseViewContract: BaseViewContract? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activityContext = activity as BaseActivity
        this.baseViewContract = this.activityContext
    }

    override fun showLoadingView(message: String) {
        this.baseViewContract?.showLoadingView(message)
    }

    override fun hideLoadingView() {
        this.baseViewContract?.hideLoadingView()

    }
}