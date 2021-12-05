package com.mamasodikov.contactgo.APIs

import com.mamasodikov.contactgo.models.BaseData
import com.mamasodikov.contactgo.models.BaseResult
import com.mamasodikov.contactgo.models.BaseTable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {
    @GET("Get")
    fun getData(@Query("GuidId") id:String, @Header("Authorization") auth:String): Call<BaseResult<BaseData<List<BaseTable>>>>
}