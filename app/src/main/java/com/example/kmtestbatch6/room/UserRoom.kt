package com.example.kmtestbatch6.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kmtestbatch6.model.UserEntity

@Database(entities = [UserEntity::class], version = 1,exportSchema = false)
abstract class UserRoom: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoom? = null
        @JvmStatic
        fun getDatabase(context: Context): UserRoom {
            if (INSTANCE == null) {
                synchronized(UserRoom::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserRoom::class.java, "users_db")
                        .build()
                }
            }
            return INSTANCE as UserRoom
        }
    }
}