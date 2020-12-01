package com.example.github.domain.usecases

import com.example.github.domain.db.repo.FollowersRepo
import com.example.github.domain.entity.Followers
import kotlinx.coroutines.flow.Flow

class FollowersUseCase(private val followersRepo: FollowersRepo) {
    suspend fun fetchFollowers(username : String): Flow<List<Followers>> =
        followersRepo.fetchFollowers(username)
}