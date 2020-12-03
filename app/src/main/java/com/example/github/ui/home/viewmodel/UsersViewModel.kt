package com.example.github.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.entity.User
import com.example.github.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.Exception

class UsersViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean> = _errorLiveData

    fun fetch() {
        _errorLiveData.value = false

        viewModelScope.launch {
            try {
                userUseCase.fetchUsers().distinctUntilChanged().collect { consumeUser(it) }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorLiveData.postValue(true)
            }

        }
    }

    private fun consumeUser(user: List<User>) {
        when (user) {
            null -> _errorLiveData.postValue(true)
            else -> {
                _usersLiveData.postValue(user)

            }

        }
    }
}

