package com.example.kotlinstudy.utils.retrofit.dao

import com.example.kotlinstudy.utils.db.entity.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import com.example.kotlinstudy.utils.Result

interface RequestInterface {

    @GET("userInfo/query")
    fun getUsers() : Call<Result<List<UserInfo>>>

    //@GET("wenda/list/{pageId}/json")
    //suspend fun getData(@Path("pageId") pageId:Int): DemoReqData
}