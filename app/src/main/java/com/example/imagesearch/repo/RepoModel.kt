package com.example.imagesearch.repo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoModel(
    val img: String,
    val title: String,
    val date: String?,
    val like: Boolean
) : Parcelable