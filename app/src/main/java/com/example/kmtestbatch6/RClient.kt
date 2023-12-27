package com.example.kmtestbatch6

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    private const val UPDATE_BASE_URL_API = "https://reqres.in"

    val apiInstance : Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UPDATE_BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}