package com.github.ihsg.appfoundation.common.base

import com.github.ihsg.appfoundation.common.util.StatusBarUtil

abstract class BaseFullscreenActivity : BaseActivity() {
    override fun initialize() {
        super.initialize()
        StatusBarUtil.fullscreen(window)
    }
}