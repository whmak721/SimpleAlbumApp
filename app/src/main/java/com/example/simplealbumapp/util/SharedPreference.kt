package com.example.simplealbumapp.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.collections.ArrayList

class SharedPreference(private val context: Context) {
    companion object {
        const val BOOKMARKED = "BOOKMARKED"

        const val DATA = "DATA"
    }

    fun saveBookmark(key: String, value: String) {
        val sharedPreference = context.getSharedPreferences(BOOKMARKED, MODE_PRIVATE)
        val editor = sharedPreference.edit()

        if (sharedPreference.getString(key, "") == "") {
            val data = arrayListOf<String>()
            data.add(value)
            editor.putString(key, Gson().toJson(data))
            editor.apply()
        } else {
            val type: Type = object : TypeToken<ArrayList<String>>() {}.type
            val data = Gson().fromJson<ArrayList<String>>(sharedPreference.getString(key, ""), type)
            if (!data.contains(value)) {
                data.add(value)
            }
            editor.putString(key, Gson().toJson(data))
            editor.apply()
        }
    }

    fun readBookmark(key: String): ArrayList<String> {
        val sharedPreference = context.getSharedPreferences(BOOKMARKED, MODE_PRIVATE)
        val type: Type = object : TypeToken<ArrayList<String>>() {}.type
        if (sharedPreference.getString(key, "") == ""){
            return arrayListOf()
        }
        return Gson().fromJson(sharedPreference.getString(key, ""), type)
    }

    fun removeBookmark(key: String, value: String) {
        val sharedPreference = context.getSharedPreferences(BOOKMARKED, MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val type: Type = object : TypeToken<ArrayList<String>>() {}.type
        val data = Gson().fromJson<ArrayList<String>>(sharedPreference.getString(key, ""), type)
        data.remove(value)
        editor.putString(key, Gson().toJson(data))
        editor.apply()

    }
}