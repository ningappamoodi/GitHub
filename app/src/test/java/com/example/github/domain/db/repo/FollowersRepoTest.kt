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

    private lateinit var articlesRepo: FollowersRepo

    @Before
    fun setUP() {
        MockKAnnotations.init(this)
        articlesRepo = FollowersRepo(followerDataSource)
    }

    @Test
    fun `fetch Followers`() {
        val articleList = DataUtils.getFollowers()

        runBlocking {
            coEvery { followerDataSource.fetchFollowers("username") } returns flow { articleList }

            articlesRepo.fetchFollowers("username").collect { assertEquals(articleList, it) }
        }
    }
}