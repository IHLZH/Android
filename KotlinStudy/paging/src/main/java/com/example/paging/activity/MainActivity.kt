package com.example.paging.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.adapter.DataRecycleViewAdapter
import com.example.paging.mvvm.MainActivityViewModel
import com.example.paging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        binding.rvData.layoutManager = manager
        val dataRecycleViewAdapter = DataRecycleViewAdapter()
        binding.rvData.adapter = dataRecycleViewAdapter
        binding.btnGet.setOnClickListener {
            lifecycleScope.launch {
                MainActivityViewModel().getData().collectLatest {
                    dataRecycleViewAdapter.submitData(it)
                }
            }
        }
    }
}