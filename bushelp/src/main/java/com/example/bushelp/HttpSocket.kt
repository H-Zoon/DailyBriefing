package com.example.bushelp

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class HttpSocket {
    companion object {
        const val URL: String = "http://apis.data.go.kr/1613000/BusSttnInfoInqireService/"
        const val KEY: String ="o9tx32mIIqrOKKs4xkwVDf7gDdcnLDx1hz9H288dygGc5kBsDvO7jcRNOkH29WRe4q7m0hWw%2BO4cbI5Q5jo%2FDA%3D%3D"

    }

    val g : Gson = GsonBuilder()
    .setLenient()
    .create()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(g))
        .build()

    private var retrofitAPI = retrofit.create(com.example.bushelp.API::class.java)

    fun getBusInfo(stationName: String, callbackResponse:(String)->Unit) {

            retrofitAPI.requestBusInfo(KEY,25, "전통시장", 44810, 10, 1, "json").enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>){
                    callbackResponse(response.toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callbackResponse(t.toString())
                    Log.d("error", "$t")
                }
        })
    }

}