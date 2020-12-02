package com.example.github.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.entity.User
import com.example.github.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User>  = _userLiveData

    private val _userFetchLiveData: MutableLiveData<String> = MutableLiveData()
    val userFetchLiveData: LiveData<String>  = _userFetchLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean>  = _errorLiveData

    fun fetch(username: String) {
        _errorLiveData.value = false

        viewModelScope.launch {
           try {
                userUseCase.fetchUser(username).distinctUntilChanged().collect { consumeUser(it) }
            } catch (e: Exception) {
                e.printStackTrace()
               _errorLiveData.postValue(true)
            }

        }
    }

    private fun consumeUser(user: User) {
        when (user) {
            null -> _errorLiveData.postValue(true)
            else -> {
                _userFetchLiveData.postValue(user.login)
                _userLiveData.postValue(user)

            }

        }
    }

    fun reset() {
        _userFetchLiveData.value = null
    }
}