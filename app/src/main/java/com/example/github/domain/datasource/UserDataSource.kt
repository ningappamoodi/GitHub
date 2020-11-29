package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(private val githubService: GithubService) {

    suspend fun fetchUser(username: String): User {
        return withContext(Dispatchers.IO) {
            githubService.fetchUser(username)
        }
    }
}