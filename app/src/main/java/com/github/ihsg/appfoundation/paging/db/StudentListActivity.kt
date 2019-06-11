package com.github.ihsg.appfoundation.paging.db

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : BaseActivity() {
    companion object {
        fun startAction(context: Context) {
            context.startActivity(Intent(context, StudentListActivity::class.java))
        }
    }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(StudentVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_student_list)

        val adapter = StudentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.allStudents.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
