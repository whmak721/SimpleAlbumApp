package com.example.simplealbumapp.webservice

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebServiceModel {
    private val url = "https://itunes.apple.com"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        //val token = ""
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor { chain ->
            val apiRequest = chain.request()
            val apiResponse = chain.proceed(apiRequest)
            when (apiResponse.code()) {
                200 -> Log.d("test", "success")
                404 -> Log.d("test", "fail")
            }
            apiResponse
        }
        return okHttpBuilder.build()
    }
}