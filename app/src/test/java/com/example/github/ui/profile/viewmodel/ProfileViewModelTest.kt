package com.example.github.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.github.DataUtils
import com.example.github.domain.entity.User
import com.example.github.domain.usecases.UserUseCase
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

class ProfileViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var userUseCase: UserUseCase

    @MockK
    lateinit var userLiveData: MutableLiveData<User>

    @MockK
    lateinit var errorLiveData: MutableLiveData<Boolean>

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        val viewModel = ProfileViewModel(userUseCase)
        profileViewModel = spyk(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test User fetch`() {
        val user = DataUtils.getUser()
        mockUserLiveData(user)

        coEvery { userUseCase.fetchUser("") } returns flow { user }

        runBlocking { profileViewModel.fetch("") }

        assertEquals(user, profileViewModel.userLiveData.value)
    }

    private fun mockUserLiveData(user: User) {
        every { profileViewModel setProperty "_userLiveData" value (userLiveData) } just runs

        every { profileViewModel setProperty "_errorLiveData" value (errorLiveData) } just runs

        every { profileViewModel.userLiveData } returns userLiveData
        every { profileViewModel.errorLiveData } returns errorLiveData
        every { userLiveData.value } returns user
    }
}