package com.example.paging.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val BASEAPI = "https://www.wanandroid.com/"

    fun <T> createService(mClass : Class<T>) : T{
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASEAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(mClass) as T
        Log.i("paging_learn_test", "createService: RetrofitService创建成功")
    }
}