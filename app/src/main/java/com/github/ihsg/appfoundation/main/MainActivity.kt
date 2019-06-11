package com.github.ihsg.appfoundation.main

import android.os.Bundle
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
    }

    override fun onPostResume() {
        super.onPostResume()
        this.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, MainFragment())
                .commit()
    }
}
