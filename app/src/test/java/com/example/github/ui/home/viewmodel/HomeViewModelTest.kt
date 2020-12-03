package com.example.github.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var hideFabLiveData: MutableLiveData<Boolean>

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        val viewModel = HomeViewModel()
        homeViewModel = spyk(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test hide fab when true`() {
        every { homeViewModel setProperty "_hideFabLiveData" value (hideFabLiveData) } just runs

        every { homeViewModel.hideFabLiveData } returns hideFabLiveData
        every { hideFabLiveData.value } returns true

      run { homeViewModel.hideFab(true) }

        assertEquals(true, homeViewModel.hideFabLiveData.value)
    }

    @Test
    fun `test hide fab when false`() {
        every { homeViewModel setProperty "_hideFabLiveData" value (hideFabLiveData) } just runs

        every { homeViewModel.hideFabLiveData } returns hideFabLiveData
        every { hideFabLiveData.value } returns false

        run { homeViewModel.hideFab(false) }

        assertEquals(false, homeViewModel.hideFabLiveData.value)
    }
}