package com.example.github.domain.repo

import com.example.github.domain.entity.User
import com.example.github.domain.datasource.UserDataSource
import kotlinx.coroutines.flow.Flow

class UserRepo(private val userDataSource: UserDataSource) {
    suspend fun fetchUser(username: String): Flow<User> = userDataSource.fetchUser(username)

    suspend fun fetchFollowers(username: String): List<User> = userDataSource.fetchFollowers(username)

    suspend fun fetchFollowing(username: String): List<User> = userDataSource.fetchFollowing(username)
}