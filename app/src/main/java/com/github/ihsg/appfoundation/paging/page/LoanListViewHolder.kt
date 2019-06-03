package com.github.ihsg.appfoundation.paging.page

import androidx.recyclerview.widget.RecyclerView
import com.github.ihsg.appfoundation.databinding.ItemLoanBinding

class LoanListViewHolder(private val bind: ItemLoanBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bindTo(loanEntity: LoanEntity?) {
        loanEntity?.let {
            bind.vm = it.toLoanItemVM()
            bind.executePendingBindings()
        }
    }
}