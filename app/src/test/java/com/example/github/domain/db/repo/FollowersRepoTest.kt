package com.example.github.domain.db.repo

import com.example.github.DataUtils
import com.example.github.domain.datasource.FollowerDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FollowersRepoTest {
    @MockK
    private lateinit var followerDataSource: FollowerDataSource

    private lateinit var followersRepo: FollowersRepo

    @Before
    fun setUP() {
        MockKAnnotations.init(this)
        followersRepo = FollowersRepo(followerDataSource)
    }

    @Test
    fun `fetch Followers`() {
        val followers = DataUtils.getFollowers()

        runBlocking {
            coEvery { followerDataSource.fetchFollowers("username") } returns flow { followers }

            followersRepo.fetchFollowers("username").collect { assertEquals(followers, it) }
        }
    }
}