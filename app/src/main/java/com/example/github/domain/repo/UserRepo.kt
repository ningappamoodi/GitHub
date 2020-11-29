package com.example.github.domain.repo

import com.example.github.data.model.User
import com.example.github.domain.datasource.UserDataSource

class UserRepo(private val userDataSource: UserDataSource) {
    suspend fun fetchUser(username: String): User = userDataSource.fetchUser(username)
}