package com.example.github.domain.db.repo

import com.example.github.domain.datasource.FollowerDataSource
import com.example.github.domain.entity.Followers
import kotlinx.coroutines.flow.Flow

class FollowersRepo(private val followersDataSource: FollowerDataSource) {
    suspend fun fetchFollowers(username: String): Flow<List<Followers>> =
        followersDataSource.fetchFollowers(username)
}