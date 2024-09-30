package com.example.kotlinstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ActivityMainBinding
import com.example.kotlinstudy.utils.db.entity.UserInfo
import com.example.kotlinstudy.ui.fragment.HomeFragment
import com.example.kotlinstudy.ui.fragment.MineFragment
import com.example.kotlinstudy.viewmodel.MyViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding : ActivityMainBinding;
    private  var thisFragemntId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null){
            thisFragemntId = savedInstanceState.getInt("thisFragmentId")
        }
        if(thisFragemntId == null)thisFragemntId = 0

        initTab()
        initViewModel()
    }

    private fun initViewModel(){
        //获取ViewModel视图对象
        var viewModel : MyViewModel = ViewModelProvider(
            this,
            MyViewModel.MyViewModelFactory(application)
        ).get(MyViewModel::class.java)

        //为ViewModel中获取的LiveData数据设置observe监听，当数据库中数据改变时，自动回调onChanged方法
        viewModel.query().observe(this, object : Observer<List<UserInfo>>{
            override fun onChanged(value: List<UserInfo>) {
                Log.i(TAG, "onChanged: Observer#onChanged回调, List<UserInfo> : " + value)
            }
        })
    }

    private fun initTab(){
        val tab : TabLayout = binding.mainTab;

        tab.addTab(tab.newTab().setText("Login"))
        tab.addTab(tab.newTab().setText("Mine"))

        when(thisFragemntId){
            0 -> loadFragment(HomeFragment())
            else -> loadFragment(MineFragment())
        }


        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                thisFragemntId = tab?.position!!
                val fragment = when(tab?.position){
                    0 -> HomeFragment()
                    1 -> MineFragment()
                    else -> null
                }
                //使用let安全调用fragment作为参数的方法
                fragment?.let { loadFragment(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("Not yet implemented")
            }

        })
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fcv, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("thisFragmetId", thisFragemntId!!)
    }



}