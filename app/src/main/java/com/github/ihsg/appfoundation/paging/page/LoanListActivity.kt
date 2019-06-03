package com.github.ihsg.appfoundation.paging.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.base.BaseFullscreenActivity
import com.github.ihsg.appfoundation.common.util.LogUtil
import com.github.ihsg.appfoundation.databinding.ActivityLoanListBinding
import kotlinx.android.synthetic.main.activity_loan_list.*

class LoanListActivity : AppCompatActivity() {

    companion object {
        fun startAction(context: Context) {
            context.startActivity(Intent(context, LoanListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val d = DataBindingUtil.setContentView<ActivityLoanListBinding>(this, R.layout.activity_loan_list)
        d.lifecycleOwner = this
        d.layoutMgr = LinearLayoutManager(this)
        d.adapter = this.adapter
        ButterKnife.bind(this)

        this.newViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return NewLoanListVM() as T
            }
        })[NewLoanListVM::class.java]

        this.newViewModel.loans.observe(this, Observer { adapter.submitList(it) })
        this.newViewModel.initialState.observe(this, Observer { LogUtil.v("initial: ${it.status.name}") })
        this.newViewModel.refreshState.observe(this, Observer { LogUtil.v("refresh: ${it.status.name}") })
        this.newViewModel.moreState.observe(this, Observer { LogUtil.v("more: ${it.status.name}") })
    }

    private lateinit var viewModel: LoanListVM
    private val adapter: LoanListAdapter by lazy { LoanListAdapter() }
    private lateinit var newViewModel: NewLoanListVM

    @OnClick(R.id.btnRefresh)
    fun onClickRefresh() {
//        this.viewModel.getLoanList().value?.dataSource?.invalidate()
//        this.loadLoanList()
//        this.viewModel.refresh()
        this.newViewModel.refresh()
    }

    @OnClick(R.id.btnRetry)
    fun onClickRetry() {
        this.newViewModel.retry()
    }

    private fun loadLoanList() {
        this.viewModel.getLoanList().observe(this,
                Observer<PagedList<LoanEntity>> {
                    adapter.submitList(it)
                })
    }
}
