package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.domain.db.dao.FollowersDao
import com.example.github.domain.entity.Followers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FollowingDataSource (private val followersDao: FollowersDao, private val githubService: GithubService) {

    suspend fun fetchFollowers(username: String): Flow<List<Followers>> {
        withContext(Dispatchers.IO) {
            githubService.fetchFollowers(username).also {
                followersDao.insertFollowers(it) }
        }
        return followersDao.getFollowers(username)
    }
}