package com.github.ihsg.appfoundation.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var toggle = false
    private val viewModel: LoginVM by lazy {
        ViewModelProviders.of(this).get(LoginVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}
