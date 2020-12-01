package com.example.github.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.entity.User
import com.example.github.domain.usecases.UserUseCase
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User>  = _userLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean>  = _errorLiveData

    fun fetch(username: String) {
        _errorLiveData.value = false

        viewModelScope.launch {
           try {
                userUseCase.fetchUser(username).collect { consumeUser(it) }
            } catch (e: Exception) {
                e.printStackTrace()
               _errorLiveData.postValue(true)
            }

        }
    }

    private fun consumeUser(user: User) {
        when (user) {
            null -> _errorLiveData.postValue(true)
            else -> _userLiveData.postValue(user)
        }
    }
}