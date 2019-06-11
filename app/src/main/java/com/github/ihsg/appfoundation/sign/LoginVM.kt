package com.github.ihsg.appfoundation.sign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ihsg.appfoundation.common.util.LogUtil

class LoginVM : ViewModel() {
    private var a = 0
    val textWelcome = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val inputUserName = MutableLiveData<String>()

    fun onSubmit() {
        this.textWelcome.value = "Welcome ${++a}"
        this.imageUrl.postValue("https://www.lvjinsuo.com/static/images/app/banner/depository_banner.png")
        LogUtil.i("input user name: ${inputUserName.value}")
    }
}