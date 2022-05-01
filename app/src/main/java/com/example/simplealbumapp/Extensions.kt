package com.example.simplealbumapp

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
    return outputFormat.format(inputFormat.parse(this))
}