package com.example.kmtestbatch6.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kmtestbatch6.RClient
import com.example.kmtestbatch6.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    var userLiveData: MutableLiveData<UserResponse> = MutableLiveData()

    fun getUserData() : MutableLiveData<UserResponse> {
        return userLiveData
    }

    fun callUserData(page: Int) {
        RClient.apiInstance.getUser(page)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        userLiveData.postValue(response.body())
                    } else {
                        Log.d("Fetch Data User Failed", call.toString())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("User Data Error", call.toString())
                }

            })
    }
}