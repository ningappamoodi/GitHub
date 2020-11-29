package com.example.github.domain.usecases

import com.example.github.data.model.User
import com.example.github.domain.repo.UserRepo

class UserUseCase(private val userRepo: UserRepo) {

    suspend fun fetchUser(username : String): User = userRepo.fetchUser(username)
}