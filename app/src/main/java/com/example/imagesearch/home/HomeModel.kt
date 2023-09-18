package com.example.imagesearch.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeModel(
    val img: String,
    val title: String,
    val date: String?
) : Parcelable