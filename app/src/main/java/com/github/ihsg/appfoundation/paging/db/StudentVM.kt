package com.github.ihsg.appfoundation.paging.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ihsg.appfoundation.common.config.AppConfig

class StudentVM(app:Application): AndroidViewModel(app) {
    val dao = AppConfig.getDBInstance().studentDao()

    val allStudents = LivePagedListBuilder(
        dao.getAllStudent(),
        PagedList.Config.Builder()
            .setPageSize(5)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .build())
        .build()
}