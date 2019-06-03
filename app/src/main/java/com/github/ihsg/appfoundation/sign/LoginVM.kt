package com.github.ihsg.appfoundation.sign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginVM : ViewModel() {
    private var a = 0
    val textWelcome = MutableLiveData<String>()

    fun onSubmit() {
        this.textWelcome.postValue("Welcome ${++a}")
    }
}