package com.example.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("{BUS}")
    fun requestBusInfo(@Path("BUS") BUS: String): Call<String>
}