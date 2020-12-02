package com.example.github.domain.datasource

import com.example.github.DataUtils
import com.example.github.data.apiservices.GithubService
import com.example.github.domain.db.dao.UserDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDataSourceTest {

    @MockK
    private lateinit var userDao: UserDao
    @MockK
    private lateinit var githubService:GithubService

    private lateinit var userDataSource: UserDataSource


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDataSource = UserDataSource(userDao, githubService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch User`() {

        val user = DataUtils.getUser()

        runBlocking {

            coEvery { userDao.getUser("username") } returns flow {}
            coEvery { userDao.deleteAndCreateUser(user) } returns Unit
            coEvery { userDao.insertUser(user) } returns Unit
            coEvery { githubService.fetchUser(any()) } returns user

            userDataSource.fetchUser("username").collect { assertEquals(user, it) }
        }
    }
}