package com.example.github.domain.db.repo

import com.example.github.domain.datasource.FollowingDataSource
import com.example.github.domain.entity.Following
import kotlinx.coroutines.flow.Flow

class FollowingRepo(private val followingDataSource: FollowingDataSource) {
    suspend fun fetchFollowing(username: String): Flow<List<Following>> =
        followingDataSource.fetchFollowings(username)
}