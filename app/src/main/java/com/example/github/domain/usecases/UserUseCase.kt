package com.example.github.domain.usecases

import com.example.github.domain.entity.User
import com.example.github.domain.repo.UserRepo
import kotlinx.coroutines.flow.Flow

class UserUseCase(private val userRepo: UserRepo) {

    suspend fun fetchUser(username : String): Flow<User> = userRepo.fetchUser(username)

    suspend fun fetchFollowers(username : String): List<User> = userRepo.fetchFollowers(username)

    suspend fun fetchFollowing(username : String): List<User> = userRepo.fetchFollowing(username)
}