package com.github.ihsg.appfoundation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ihsg.appfoundation.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onPostResume() {
        super.onPostResume()

        this.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, HomeFragment())
            .commit()
    }
}
