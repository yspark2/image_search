package com.example.imagesearch.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagesearch.home.HomeModel
import com.example.imagesearch.repo.RepoModel

class SharedViewModel: ViewModel() {
    val selectedItem = MutableLiveData<HomeModel>()
}