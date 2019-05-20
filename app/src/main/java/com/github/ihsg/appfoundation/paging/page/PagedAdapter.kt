package com.github.ihsg.appfoundation.paging.page

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class PagedAdapter : PagedListAdapter<LoanEntity, PagedViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedViewHolder =
        PagedViewHolder(parent)


    override fun onBindViewHolder(holder: PagedViewHolder, position: Int) {
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