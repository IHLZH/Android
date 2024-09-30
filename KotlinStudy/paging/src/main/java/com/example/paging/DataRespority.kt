package com.example.paging

import com.example.paging.data.DataApi
import com.example.paging.data.DemoReqData
import com.example.paging.data.RetrofitService

class DataRespority {
    private var netWork = RetrofitService().createService(
        DataApi::class.java
    )

    suspend fun loadData(
        pageId : Int
    ): DemoReqData?{
        return try{
            netWork.getData(pageId)
        }catch (e : Exception){
            null
        }
    }
}