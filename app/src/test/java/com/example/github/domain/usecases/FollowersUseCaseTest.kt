package com.example.github.domain.usecases

import com.example.github.DataUtils
import com.example.github.domain.db.repo.FollowersRepo
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

class FollowersUseCaseTest {

    @MockK
    private lateinit var followersRepo: FollowersRepo
    private lateinit var followersUseCase: FollowersUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        followersUseCase = FollowersUseCase(followersRepo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch Followers`() {

        val followersList = DataUtils.getFollowers()

        runBlocking {
            val flow = flow { emit(followersList) }
            coEvery { followersRepo.fetchFollowers("") } returns flow

            followersUseCase.fetchFollowers("").collect { assertEquals(followersList, it) }
        }
    }
}