package com.example.paging.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.data.DemoReqData
import com.example.paging.databinding.ItemDataBinding

class DataRecycleViewAdapter : PagingDataAdapter<DemoReqData.DataBean.DatasBean, RecyclerView.ViewHolder>(
    object :
        DiffUtil.ItemCallback<DemoReqData.DataBean.DatasBean>() {

        override fun areItemsTheSame(
            oldItem: DemoReqData.DataBean.DatasBean,
            newItem: DemoReqData.DataBean.DatasBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: DemoReqData.DataBean.DatasBean,
            newItem: DemoReqData.DataBean.DatasBean
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var datasBean = getItem(position)
        (holder as DataViewHolder).binding.demoReaData = datasBean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_data,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    inner class DataViewHolder(private val dataBindingUtil: ItemDataBinding) :
        RecyclerView.ViewHolder(dataBindingUtil.root) {
        var binding = dataBindingUtil
    }
}