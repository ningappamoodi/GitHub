package com.example.github.ui.following.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.github.DataUtils
import com.example.github.domain.entity.Following
import com.example.github.domain.usecases.FollowingUseCase
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

class FollowingViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var followingUseCase: FollowingUseCase

    @MockK
    lateinit var followingLiveData: MutableLiveData<List<Following>>

    @MockK
    lateinit var errorLiveData: MutableLiveData<Boolean>

    private lateinit var followingViewModel: FollowingViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        val viewModel = FollowingViewModel(followingUseCase)
        followingViewModel = spyk(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test Followings fetch`() {
        val followingsList = DataUtils.getFollowings()
        mockFollowingsLiveData(followingsList)

        coEvery { followingUseCase.fetchFollowing("") } returns flow { followingsList }

        runBlocking { followingViewModel.fetch("") }

        assertEquals(followingsList, followingViewModel.followingLiveData.value)
    }

    private fun mockFollowingsLiveData(followingsList: List<Following>) {
        every { followingViewModel setProperty "_followingLiveData" value (followingLiveData) } just runs

        every { followingViewModel setProperty "_errorLiveData" value (errorLiveData) } just runs

        every { followingViewModel.followingLiveData } returns followingLiveData
        every { followingViewModel.errorLiveData } returns errorLiveData
        every { followingLiveData.value } returns followingsList
    }
}