package com.example.github.domain.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.github.domain.entity.User

@Database(entities = [User::class], version = 3)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}