package com.example.imagesearch.home

import android.os.Parcelable
import com.example.imagesearch.repo.RepoModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeModel(
    val img: String,
    val title: String,
    val date: String?,
    val like: Boolean = false
) : Parcelable

fun HomeModel.toRepoModel(): RepoModel{
    return RepoModel(
        img = img,
        title = title,
        date = date,
        like = like
    )
}