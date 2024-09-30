package com.example.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.data.DemoReqData

class DataSource : PagingSource<Int, DemoReqData.DataBean.DatasBean>() {
    override fun getRefreshKey(state: PagingState<Int, DemoReqData.DataBean.DatasBean>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DemoReqData.DataBean.DatasBean> {
        return try{
            var currentPage = params.key ?: 1
            var demoReqData = DataRespority().loadData(currentPage)
            var nextPage = if(currentPage > demoReqData?.data?.pageCount ?: 0){
                currentPage + 1
            }else{
                null
            }

            if(demoReqData != null){
                LoadResult.Page(
                    data = demoReqData.data.datas,
                    prevKey = null,
                    nextKey = nextPage
                )
            }else{
                LoadResult.Error(throwable = Throwable())
            }
        }catch (e : Exception){
            LoadResult.Error(throwable = e)
        }
    }
}