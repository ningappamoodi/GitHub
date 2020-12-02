package com.example.github.domain.usecases

import com.example.github.domain.db.repo.FollowingRepo
import com.example.github.domain.entity.Following
import kotlinx.coroutines.flow.Flow

class FollowingUseCase(private val followingRepo: FollowingRepo) {
    suspend fun fetchFollowing(username : String): Flow<List<Following>> =
        followingRepo.fetchFollowing(username)
}