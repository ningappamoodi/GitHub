package com.example.github.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.github.domain.db.dao.FollowersDao
import com.example.github.domain.db.dao.FollowingDao
import com.example.github.domain.db.dao.UserDao
import com.example.github.domain.entity.Followers
import com.example.github.domain.entity.Following
import com.example.github.domain.entity.User

@Database(entities = [User::class, Followers::class, Following::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun followersDao() : FollowersDao
    abstract fun followingDao() : FollowingDao
}