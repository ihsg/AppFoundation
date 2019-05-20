package com.github.ihsg.appfoundation.paging.db

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ihsg.appfoundation.R

class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private var studentEntity: StudentEntity? = null

    fun bindTo(studentEntity: StudentEntity?) {
        this.studentEntity = studentEntity
        this.nameView.text = studentEntity?.name
    }
}