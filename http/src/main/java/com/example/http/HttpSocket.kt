package com.example.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpSocket() {
    companion object {
        const val URL: String = "https://www.google.com/"
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var retrofitAPI = retrofit.create(API::class.java)

    fun getBusInfo(busNumber: Int, station: String, callbackResponse:(String)->Unit) {

            retrofitAPI.requestBusInfo(station).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>){
                    callbackResponse(response.toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callbackResponse(t.toString())
                }
        })
    }

    fun getWeatherInfo(region: String): String {
        return "날씨리턴 예시입니다."
    }
}