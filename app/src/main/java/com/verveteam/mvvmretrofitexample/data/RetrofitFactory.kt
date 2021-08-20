package com.verveteam.mvvmretrofitexample.data

import com.verveteam.mvvmretrofitexample.data.api.TypicodeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val typicodeService: TypicodeService = getRetrofit().create(TypicodeService::class.java)
}