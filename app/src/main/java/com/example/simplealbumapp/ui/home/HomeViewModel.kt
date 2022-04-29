package com.example.simplealbumapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplealbumapp.ApiRepository
import com.example.simplealbumapp.model.AlbumList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val albumDataList = MutableLiveData<AlbumList>()

    private val apiRepository: ApiRepository by lazy { ApiRepository() }

    fun getAlbumList() {
        apiRepository.getAlbumList().enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                albumDataList.value = response.body()
            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
            }

        })
    }

    val text: LiveData<String> = _text
}