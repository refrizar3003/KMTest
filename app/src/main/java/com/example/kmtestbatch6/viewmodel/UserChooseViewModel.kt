package com.example.kmtestbatch6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kmtestbatch6.model.UserEntity
import com.example.kmtestbatch6.room.UserDao
import com.example.kmtestbatch6.room.UserRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserChooseViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: UserDao?
    private var userDatabase: UserRoom?

    init {
        userDatabase = UserRoom.getDatabase(application)
        userDao = userDatabase?.userDao()
    }

    fun addUser(
        id: Int,
        firstName: String,
        lastName: String,
        avatar: String,
        email: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = UserEntity(id, firstName,lastName, avatar, email)
            userDao?.insertFavorite(movie)
        }
    }


    fun getUser(): LiveData<List<UserEntity>>? {
        return userDao?.getDataFavorite()
    }

    fun deleteUser(id: Int,
                   firstName: String,
                   lastName: String,
                   avatar: String,
                   email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = UserEntity(id, firstName, lastName, avatar, email)
            userDao?.deleteFavorite(movie)
        }
    }
}