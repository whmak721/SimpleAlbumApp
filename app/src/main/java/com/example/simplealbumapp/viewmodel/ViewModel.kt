package com.example.simplealbumapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplealbumapp.webservice.ApiRepository
import com.example.simplealbumapp.model.AlbumList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
    private val apiRepository: ApiRepository by lazy { ApiRepository() }

    private val _albumDataList = MutableLiveData<AlbumList>()
    val albumDataList: LiveData<AlbumList> = _albumDataList

    fun getAlbumList() {
        apiRepository.getAlbumList().enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                _albumDataList.value = response.body()
            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
            }

        })
    }
}