package com.example.github.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.model.User
import com.example.github.domain.usecases.UserUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _userLiveData: MutableLiveData<User> = MutableLiveData()
    val userListLiveData: LiveData<User>  = _userLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean>  = _errorLiveData

    fun fetch(username: String) {
        _errorLiveData.value = false

        viewModelScope.launch {
            val userList = try {
                userUseCase.fetchUser(username)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            if (userList == null) _errorLiveData.postValue(true)
            else _userLiveData.postValue(userList)
        }
    }
}