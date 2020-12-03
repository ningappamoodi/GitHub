package com.example.github.domain.db.repo

import com.example.github.DataUtils
import com.example.github.domain.datasource.UserDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepoTest {

    @MockK
    private lateinit var userDataSource: UserDataSource

    private lateinit var userRepo: UserRepo

    @Before
    fun setUP() {
        MockKAnnotations.init(this)
        userRepo = UserRepo(userDataSource)
    }

    @Test
    fun `fetch User`() {
        val user = DataUtils.getUser()

        runBlocking {
            coEvery { userDataSource.fetchUser("username") } returns flow { user }

            userRepo.fetchUser("username").collect { assertEquals(user, it) }
        }
    }

    @Test
    fun `fetch Users`() {
        val user = DataUtils.getUsers()

        runBlocking {
            coEvery { userDataSource.fetchUsers() } returns flow { user }

            userRepo.fetchUsers().collect { assertEquals(user, it) }
        }
    }
}