package com.verveteam.mvvmretrofitexample.data

import com.verveteam.mvvmretrofitexample.data.model.Post

sealed class Result {

    class Success(val data: List<Post>) : Result()
    class Failure : Result()
}