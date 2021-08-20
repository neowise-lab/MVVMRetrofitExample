package com.verveteam.mvvmretrofitexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verveteam.mvvmretrofitexample.data.PostRepository
import com.verveteam.mvvmretrofitexample.data.RetrofitFactory
import com.verveteam.mvvmretrofitexample.data.Result

class MainViewModel : ViewModel() {

    private val postRepository = PostRepository(RetrofitFactory.typicodeService)
    private val postLiveData = MutableLiveData<Result>()
    private val stateLiveData = MutableLiveData(UIState.Complete)

    val posts: LiveData<Result> = postLiveData
    val uiState: LiveData<UIState> = stateLiveData

    fun loadPosts() {
        postRepository.getPosts(postLiveData, stateLiveData)
    }
}