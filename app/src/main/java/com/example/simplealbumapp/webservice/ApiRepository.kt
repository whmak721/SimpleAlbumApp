package com.example.simplealbumapp.webservice

import com.example.simplealbumapp.model.AlbumList
import retrofit2.Call


class ApiRepository {
    private val apiService: ApiService by lazy {
        WebServiceModel().getRetrofit().create(ApiService::class.java)
    }

    fun getAlbumList(): Call<AlbumList> {
        return apiService.getAlbumList("jack johnson","album")
    }
}