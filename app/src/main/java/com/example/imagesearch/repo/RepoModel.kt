package com.example.imagesearch.repo

import android.os.Parcelable
import com.example.imagesearch.home.HomeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoModel(
    val img: String,
    val title: String,
    val date: String?,
    val like: Boolean = false
) : Parcelable {

}


// HomeModel 객체를 받아와 RepoModel 객체로 변환
fun HomeModel.toRepoModel(): RepoModel{
    return RepoModel(
        img = img,
        title = title,
        date = date,
        like = like
    )
}