package com.example.simplealbumapp.model

import android.content.Context
import com.example.simplealbumapp.util.SharedPreference

data class Album(
    val wrapperType: String,
    val collectionType: String,
    val artistId: Int,
    val collectionId: Int,
    val amgArtistId: Int,
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: String,
    val trackCount: Int,
    val copyright: String,
    val country: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String,
) {
    fun isBookmarked(context: Context): Boolean {
        return SharedPreference(context).readBookmark(SharedPreference.DATA).any {
            it == collectionId.toString()
        }
    }

    val isExplicit: Boolean
        get() = collectionExplicitness == "explicit"

    val formattedPrice: String
        get() = "$currency $collectionPrice"
}
