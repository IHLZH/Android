package com.example.kotlinstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ActivityTestBinding
import com.example.kotlinstudy.viewmodel.TestViewModel

class TestActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTestBinding

    private lateinit var testViewModel : TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        //setContentView(R.layout.activity_test)
        initViewModel();
        //initBtn();
    }

    private fun initViewModel(){
        testViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TestViewModel::class.java)

        binding.testdata = testViewModel
        binding.lifecycleOwner = this

//        testViewModel.getNum().observe(this, object : Observer<Int>{
//            override fun onChanged(value: Int) {
//                binding.tvTest2.setText(value.toString())
//            }
//        })
    }

    private fun initBtn(){
        binding.btnText.setOnClickListener {
            testViewModel.addNum(1)
        }
    }

}