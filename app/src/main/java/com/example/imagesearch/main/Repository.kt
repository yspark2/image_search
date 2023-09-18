package com.example.imagesearch.main

import com.example.imagesearch.BuildConfig
import retrofit2.Response

class Repository {
    private val apiKey = "KakaoAK ${BuildConfig.api_key}"

    suspend fun searchImage(query: String, sort: String): Response<ImageSearchResponse> {
        return RetrofitInstance.api.searchImage(
            apiKey,
            query = query, sort = sort, page = 1, size = 20
        )
    }
}