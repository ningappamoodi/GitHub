package com.example.github.domain.datasource

import com.example.github.DataUtils
import com.example.github.data.apiservices.GithubService
import com.example.github.domain.db.dao.FollowersDao
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

class FollowerDataSourceTest {

    @MockK
    private lateinit var followersDao: FollowersDao
    @MockK
    private lateinit var githubService: GithubService

    private lateinit var followerDataSource: FollowerDataSource


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        followerDataSource = FollowerDataSource(followersDao, githubService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch Followers`() {

        val followersList = DataUtils.getFollowers()

        runBlocking {

            coEvery { followersDao.getFollowers("username") } returns flow {}
            coEvery { followersDao.deleteAndCreateFollowers("username", followersList) } returns Unit
            coEvery { followersDao.insertFollowers(followersList) } returns Unit
            coEvery { githubService.fetchFollowers(any()) } returns followersList

            followerDataSource.fetchFollowers("username").collect { assertEquals(followersList, it) }
        }
    }
}