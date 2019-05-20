package com.github.ihsg.appfoundation.paging.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_loan_list.*

class LoanListActivity : BaseActivity() {
    companion object {
        fun startAction(context: Context) {
            context.startActivity(Intent(context, LoanListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_list)

        val adapter = PagedAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PagedVM() as T
            }

        })[PagedVM::class.java]

        viewModel.getLoanList().observe(this,
            Observer<PagedList<LoanEntity>> {
                adapter.submitList(it)
            })
    }
}
