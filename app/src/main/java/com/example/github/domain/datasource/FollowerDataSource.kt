package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.domain.entity.Followers
import com.example.github.domain.db.dao.FollowersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FollowerDataSource(private val followersDao: FollowersDao, private val githubService: GithubService) {

    suspend fun fetchFollowers(username: String): Flow<List<Followers>> {
         withContext(Dispatchers.IO) {
            githubService.fetchFollowers(username).map<Followers, Followers>
            { followers: Followers -> followers.username = username;followers }
                .also { followersDao.deleteAndCreateFollowers(username, it) }
        }
        return followersDao.getFollowers(username)
    }
}