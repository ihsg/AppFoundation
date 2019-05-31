package com.github.ihsg.appfoundation.sign

import butterknife.ButterKnife
import butterknife.OnClick
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private var toggle = false

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initialize() {
        super.initialize()
        ButterKnife.bind(this)
    }

    @OnClick(R.id.btnLogin)
    fun onClickLogin() {
        this.toggle = !this.toggle
        this.passwordWrapper.error = if (this.toggle) "error" else null
    }
}
