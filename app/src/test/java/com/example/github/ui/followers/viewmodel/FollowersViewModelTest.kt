package com.example.github.ui.followers.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.github.DataUtils
import com.example.github.domain.entity.Followers
import com.example.github.domain.usecases.FollowersUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FollowersViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var followersUseCase: FollowersUseCase

    @MockK
    lateinit var followersLiveData: MutableLiveData<List<Followers>>

    @MockK
    lateinit var errorLiveData: MutableLiveData<Boolean>

    private lateinit var followersViewModel: FollowersViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        val viewModel = FollowersViewModel(followersUseCase)
        followersViewModel = spyk(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test Followers fetch`() {
        val followersList = DataUtils.getFollowers()
        mockFollowersLiveData(followersList)

        coEvery { followersUseCase.fetchFollowers("") } returns flow { followersList }

        runBlocking { followersViewModel.fetch("") }

        assertEquals(followersList, followersViewModel.followersLiveData.value)
    }

    private fun mockFollowersLiveData(followersList: List<Followers>) {
        every { followersViewModel setProperty "_followersLiveData" value (followersLiveData) } just runs

        every { followersViewModel setProperty "_errorLiveData" value (errorLiveData) } just runs

        every { followersViewModel.followersLiveData } returns followersLiveData
        every { followersViewModel.errorLiveData } returns errorLiveData
        every { followersLiveData.value } returns followersList
    }
}