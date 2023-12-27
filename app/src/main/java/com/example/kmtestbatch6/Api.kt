package com.example.kmtestbatch6

import com.example.kmtestbatch6.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api/users")
    fun getUser(@Query("page") page: Int) : Call<UserResponse>
}