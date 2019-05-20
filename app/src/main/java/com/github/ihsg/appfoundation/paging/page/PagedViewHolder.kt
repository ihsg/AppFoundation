package com.github.ihsg.appfoundation.paging.page

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ihsg.appfoundation.R
import com.github.ihsg.appfoundation.common.exts.toCurrencyWithUnitStr
import com.github.ihsg.appfoundation.common.exts.toDateTime

class PagedViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_loan, parent, false)
) {

    private val idView = itemView.findViewById<TextView>(R.id.loanId)
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private val amountView = itemView.findViewById<TextView>(R.id.amount)
    private val createTimeView = itemView.findViewById<TextView>(R.id.createTime)
    var loanEntity: LoanEntity? = null

    fun bindTo(loanEntity: LoanEntity?) {
        this.loanEntity = loanEntity
        this.idView.text = this.loanEntity?.loanId.toString()
        this.nameView.text = this.loanEntity?.name
        this.amountView.text = this.loanEntity?.amount?.toCurrencyWithUnitStr()
        this.createTimeView.text = this.loanEntity?.createTime?.toDateTime()
    }
}