package com.github.ihsg.appfoundation.paging.db

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class StudentAdapter : PagedListAdapter<StudentEntity, StudentViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
        StudentViewHolder(parent)


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<StudentEntity>() {
            override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean =
                oldItem.id == oldItem.id
        }
    }
}
