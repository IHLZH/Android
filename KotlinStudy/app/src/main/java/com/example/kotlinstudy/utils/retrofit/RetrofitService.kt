package com.example.kotlinstudy.utils.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    var retrofit : Retrofit? = null

    private val BASEAPI : String = "https://www.wanandroid.com/"

    fun getInstance() : Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("http://10.7.86.128:8080/JMcomic/")
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }
}