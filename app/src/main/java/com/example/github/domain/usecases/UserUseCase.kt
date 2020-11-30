package com.example.github.domain.usecases

import com.example.github.domain.entity.User
import com.example.github.domain.repo.UserRepo

class UserUseCase(private val userRepo: UserRepo) {

    suspend fun fetchUser(username : String): User = userRepo.fetchUser(username)

    suspend fun fetchFollowers(username : String): List<User> = userRepo.fetchFollowers(username)

    suspend fun fetchFollowing(username : String): List<User> = userRepo.fetchFollowing(username)
}