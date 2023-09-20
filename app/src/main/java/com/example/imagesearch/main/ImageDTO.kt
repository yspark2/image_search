package com.example.imagesearch.main

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.sql.Date
import java.time.format.DateTimeFormatter

data class KakaoImage(
    @SerializedName("display_sitename")
    val siteName: String,
    val collection: String,
    val image_url: String,
    val title: String,
    val contents: String,
    @SerializedName("datetime")
    val dateTime: String,
)

data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int?,

    @SerializedName("is_end")
    val isEnd: Boolean?

)

data class ImageSearchResponse(
    @SerializedName("meta")
    val metaData: MetaData?,

    @SerializedName("documents")
    val documents: MutableList<KakaoImage>?
)

interface SimpleApi {

    @GET("v2/search/image")
    suspend fun searchImage(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ImageSearchResponse>
}

class Constants {
    companion object {
        const val BASE_URL = "https://dapi.kakao.com"
    }
}