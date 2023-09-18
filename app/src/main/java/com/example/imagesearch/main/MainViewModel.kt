package com.example.imagesearch.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myCustomPosts: MutableLiveData<Response<ImageSearchResponse>> = MutableLiveData()

    fun searchImage(query: String){
        viewModelScope.launch {
            val response = repository.searchImage(query, "accuracy")
            myCustomPosts.value = response
        }
    }
}