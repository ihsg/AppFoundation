package com.github.ihsg.appfoundation.paging.page

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class LoanListAdapter : PagedListAdapter<LoanEntity, LoanListViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanListViewHolder =
        LoanListViewHolder(parent)


    override fun onBindViewHolder(holder: LoanListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<LoanEntity>() {
            override fun areItemsTheSame(oldItem: LoanEntity, newItem: LoanEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: LoanEntity, newItem: LoanEntity): Boolean =
                oldItem.loanId == oldItem.loanId
        }
    }
}