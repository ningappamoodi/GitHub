package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.domain.entity.User
import com.example.github.domain.repo.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSource(private val userDao: UserDao, private val githubService: GithubService) {

    suspend fun fetchUser(username: String): Flow<User> {
           withContext(Dispatchers.IO) {
            githubService.fetchUser(username).also { userDao.insertUser(it) }
        }

        return userDao.getUser(username)
    }

    suspend fun fetchFollowers(username: String): List<User> {
        return withContext(Dispatchers.IO) {
            githubService.fetchFollowers(username)
        }
    }

    suspend fun fetchFollowing(username: String): List<User> {
        return withContext(Dispatchers.IO) {
            githubService.fetchFollowing(username)
        }
    }
}