package com.verveteam.mvvmretrofitexample.data.api

import com.verveteam.mvvmretrofitexample.data.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface TypicodeService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}