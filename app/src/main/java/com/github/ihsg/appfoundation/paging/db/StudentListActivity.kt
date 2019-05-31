package com.github.ihsg.appfoundation.paging.db

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_student_list
    }

    companion object {
        fun startAction(context: Context) {
            context.startActivity(Intent(context, StudentListActivity::class.java))
        }
    }


    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return StudentVM(application) as T
            }
        }).get(StudentVM::class.java)
    }


    override fun initialize() {
        super.initialize()
        val adapter = StudentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.allStudents.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
