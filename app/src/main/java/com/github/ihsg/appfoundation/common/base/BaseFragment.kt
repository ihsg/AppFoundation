package com.github.ihsg.appfoundation.common.base

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), BaseViewContract {
    private val TAG = this.javaClass.simpleName
    protected var activityContext: BaseActivity? = null
    private var baseViewContract: BaseViewContract? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activityContext = activity as BaseActivity
        this.baseViewContract = this.activityContext
    }


    override fun onResume() {
        super.onResume()
//        MobclickAgent.onPageStart(TAG)
    }

    override fun onPause() {
        super.onPause()
//        MobclickAgent.onPageEnd(TAG)
    }

    override fun showLoadingView(message: String) {
        this.baseViewContract?.showLoadingView(message)
    }

    override fun hideLoadingView() {
        this.baseViewContract?.hideLoadingView()

    }
}