package com.example.imagesearch.home

import android.os.Parcelable
import com.example.imagesearch.repo.RepoModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeModel(
    val img: String,
    val title: String,
    val date: String?,
    var like: Boolean = false
) : Parcelable
