package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.domain.entity.User
import com.example.github.domain.db.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSource(private val userDao: UserDao, private val githubService: GithubService) {

    suspend fun fetchUser(username: String): Flow<User> {

        withContext(Dispatchers.IO) {
            try {
                githubService.fetchUser(username).also { userDao.insertUser(it) }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }

        return userDao.getUser(username)
    }

     fun fetchUsers(): Flow<List<User>> = userDao.getUsers()
}