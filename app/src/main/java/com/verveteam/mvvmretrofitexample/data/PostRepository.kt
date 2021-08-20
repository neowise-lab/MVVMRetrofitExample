package com.verveteam.mvvmretrofitexample.data

import androidx.lifecycle.MutableLiveData
import com.verveteam.mvvmretrofitexample.data.api.TypicodeService
import com.verveteam.mvvmretrofitexample.data.model.Post
import com.verveteam.mvvmretrofitexample.viewmodel.UIState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(private val typicodeService: TypicodeService) {

    fun getPosts(liveData: MutableLiveData<Result>, stateLiveData: MutableLiveData<UIState>) {

        stateLiveData.postValue(UIState.Loading)

        typicodeService.getPosts().enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                liveData.postValue(Result.Success(response.body()!!))
                stateLiveData.postValue(UIState.Complete)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                liveData.postValue(Result.Failure())
                stateLiveData.postValue(UIState.Complete)
            }
        })
    }
}