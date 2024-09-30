package com.example.kotlinstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.ui.adapter.UserRvAdapter
import com.example.kotlinstudy.databinding.ItemHomepageBinding
import com.example.kotlinstudy.utils.db.entity.UserInfo
import com.example.kotlinstudy.utils.Result
import com.example.kotlinstudy.utils.retrofit.dao.RequestInterface
import com.example.kotlinstudy.utils.retrofit.RetrofitService
import com.example.kotlinstudy.viewmodel.MyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.concurrent.thread

class HomePage1Fragment : Fragment() {
    private val TAG : String = "HomePage1Fragment"
    private lateinit var binding : ItemHomepageBinding
    private lateinit var userAdapter : UserRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemHomepageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserRvAdapter(requireContext(), ArrayList<UserInfo>()) //requireContext()返回非空的context
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.adapter = userAdapter

        getUsers()
    }

    fun getUsers(){
        var userList : List<UserInfo> = ArrayList<UserInfo>()
        //1.获取retrofit实例
        var retrofit = RetrofitService().getInstance()
        //2.获取请求接口实例
        var request = retrofit.create<RequestInterface>()
        //3.获取相关get请求的call对象
        var call = request.getUsers()
        //4.发送异步网络请求
        call.enqueue(object : Callback<Result<List<UserInfo>>>{
            override fun onResponse(
                call: Call<Result<List<UserInfo>>>,
                response: Response<Result<List<UserInfo>>>
            ) {
                if(response.isSuccessful){
                    Log.i(TAG, "onResponse: " + response.body()?.msg)
                    userList = response.body()?.data ?: userList
                    Log.i(TAG, "onResponse: " + userList.size)
                    userAdapter.updateData(userList)

                    //获取ViewModel视图对象
                    var viewModel : MyViewModel = ViewModelProvider(
                        requireActivity(),
                        MyViewModel.MyViewModelFactory(requireActivity().application)
                    ).get(MyViewModel::class.java)

                    thread {
                        for(user in userList){
                            Log.i(TAG, "onResponse: " + user.toString())
                            var update = viewModel.update(user)
                            Log.i(TAG, "onResponse: update " + update)
                        }
                    }
                }
                Log.i(TAG, "onResponse: getUser请求成功")
            }
            override fun onFailure(call: Call<Result<List<UserInfo>>>, t: Throwable) {
                Log.i(TAG, "onFailure: getUsers网络请求失败 " + t.printStackTrace())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}

