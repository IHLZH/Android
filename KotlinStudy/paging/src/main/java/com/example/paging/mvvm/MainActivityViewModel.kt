package com.example.paging.mvvm

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.DataSource

class MainActivityViewModel : ViewModel() {

    fun getData() = Pager(PagingConfig(pageSize = 1)){
        DataSource()
    }.flow
}