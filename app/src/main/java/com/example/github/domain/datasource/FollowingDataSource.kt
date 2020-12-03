package com.example.github.domain.datasource

import com.example.github.data.apiservices.GithubService
import com.example.github.domain.db.dao.FollowingDao
import com.example.github.domain.entity.Following
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FollowingDataSource (private val followersDao: FollowingDao, private val githubService: GithubService) {

    suspend fun fetchFollowings(username: String): Flow<List<Following>> {
        withContext(Dispatchers.IO) {
            githubService.fetchFollowing(username).map<Following, Following>
            { following: Following -> following.username = username
                following.login = following.login.toLowerCase()
                following }
                .also { followersDao.deleteAndCreateFollowings(username, it) }
        }
        return followersDao.getFollowing(username)
    }
}