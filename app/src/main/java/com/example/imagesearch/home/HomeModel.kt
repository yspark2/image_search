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
) : Parcelable {

    fun isSameItem(other: HomeModel): Boolean{
        return title == other.title && date == other.date
    }
}
