package com.example.simplealbumapp.webservice

import com.example.simplealbumapp.model.AlbumList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun getAlbumList(@Query("term") term: String, @Query("entity") entity: String): Call<AlbumList>
}