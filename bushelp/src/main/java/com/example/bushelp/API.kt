package com.example.bushelp

import retrofit2.Call
import retrofit2.http.*
import java.lang.reflect.Type

interface API {
    @GET("getSttnNoList")
    fun requestBusInfo(
        @Query("serviceKey") string: String,
        @Query("cityCode") code: Int,
        @Query("nodeNm") name: String,
        @Query("nodeNo") stationNo: Int,
        @Query("numOfRows") row: Int,
        @Query("pageNo") pageNo: Int,
        @Query("type") type: String
    ): Call<String>
}