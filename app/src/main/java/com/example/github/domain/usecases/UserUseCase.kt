package com.example.github.domain.usecases

import com.example.github.domain.entity.User
import com.example.github.domain.db.repo.UserRepo
import kotlinx.coroutines.flow.Flow

class UserUseCase(private val userRepo: UserRepo) {

    suspend fun fetchUser(username : String): Flow<User> = userRepo.fetchUser(username)

    fun fetchUsers(): Flow<List<User>> = userRepo.fetchUsers()
}