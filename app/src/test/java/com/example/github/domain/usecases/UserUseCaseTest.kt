package com.example.github.domain.usecases

import com.example.github.DataUtils
import com.example.github.domain.db.repo.UserRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserUseCaseTest  {

    @MockK
    private lateinit var userRepo: UserRepo
    private lateinit var userUseCase: UserUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userUseCase = UserUseCase(userRepo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch User`() {

        val user = DataUtils.getUser()

        runBlocking {
            val flow = flow { emit(user) }
            coEvery { userRepo.fetchUser("") } returns flow

            userUseCase.fetchUser("").collect { assertEquals(user, it) }
        }
    }

    @Test
    fun `fetch Users`() {

        val user = DataUtils.getUsers()

        runBlocking {
            val flow = flow { emit(user) }
            coEvery { userRepo.fetchUsers() } returns flow

            userUseCase.fetchUsers().collect { assertEquals(user, it) }
        }
    }
}