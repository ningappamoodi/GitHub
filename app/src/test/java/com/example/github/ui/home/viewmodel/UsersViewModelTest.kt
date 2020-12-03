package com.example.github.ui.home.viewmodel

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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var userUseCase: UserUseCase

    @MockK
    lateinit var usersLiveData: MutableLiveData<List<User>>

    @MockK
    lateinit var errorLiveData: MutableLiveData<Boolean>

    private lateinit var usersViewModel: UsersViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        val viewModel = UsersViewModel(userUseCase)
        usersViewModel = spyk(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test User fetch`() {
        val users = DataUtils.getUsers()
        mockUserLiveData(users)

        coEvery { userUseCase.fetchUsers() } returns flow { users }

        runBlocking { usersViewModel.fetch() }

        assertEquals(users, usersViewModel.usersLiveData.value)
    }

    private fun mockUserLiveData(users: List<User>) {
        every { usersViewModel setProperty "_usersLiveData" value (usersLiveData) } just runs

        every { usersViewModel setProperty "_errorLiveData" value (errorLiveData) } just runs

        every { usersViewModel.usersLiveData } returns usersLiveData
        every { usersViewModel.errorLiveData } returns errorLiveData
        every { usersLiveData.value } returns users
    }
}