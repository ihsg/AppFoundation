package com.github.ihsg.appfoundation.common.base

interface BaseViewContract {
    fun showLoadingView(message: String = "加载中...")
    fun hideLoadingView()
}