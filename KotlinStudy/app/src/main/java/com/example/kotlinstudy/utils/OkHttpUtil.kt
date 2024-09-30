package com.example.kotlinstudy.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient

class OkHttpUtil {
    private var okHttpClient : OkHttpClient? = null;

    @Synchronized
    fun getInstance(token : String) : OkHttpClient{
        if(okHttpClient == null){
            okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor{ chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("form", "android")
                        .addHeader("Token", token)
                        .build()
                    chain.proceed(request)
                }.build()
        }
        return okHttpClient!!
    }
}