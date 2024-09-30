package com.example.kotlinstudy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kotlinstudy.ui.adapter.HomeVp2Adapter
import com.example.kotlinstudy.databinding.ItemHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment() : Fragment() {
    //Kotlin必须在声明变量时初始化，使用lateinit可以推迟初始化
    //private lateinit var btn_add : Button;

    private var binding : ItemHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomeBinding.inflate(layoutInflater)
        return binding?.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn : Button? = null;
        btn?.setOnClickListener{

        }

        val fragmentList = listOf<Fragment>(
            HomePage1Fragment(),
            HomePage2Fragment()
        )

        val adapter = HomeVp2Adapter(fragmentList, requireActivity())
        binding?.vp2Home?.adapter = adapter

        TabLayoutMediator(
            binding!!.tabHome,
            binding!!.vp2Home,
        ){
           tab, position -> tab.text = when(position){
               0 -> "HomePage1"
               1 -> "HomePage2"
                else -> null
           }
        }.attach()
    }
}