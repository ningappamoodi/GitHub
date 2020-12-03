package com.example.github.ui.following.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.entity.Following
import com.example.github.domain.usecases.FollowingUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowingViewModel(private val followingUseCase: FollowingUseCase): ViewModel() {

    private var _followingLiveData: MutableLiveData<List<Following>> = MutableLiveData()
        set(value) {}

    val followingLiveData: LiveData<List<Following>> = _followingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
        set(value) {}

    val errorLiveData: LiveData<Boolean> = _errorLiveData

    fun fetch(username: String) {
        _errorLiveData.postValue(false)

        viewModelScope.launch {
            try {
                val followings =  followingUseCase.fetchFollowing(username)
                followings.collect { consumeUser(it) }
            } catch (e: Throwable) {
                e.printStackTrace()
                _errorLiveData.postValue(true)
            }

        }
    }

    private fun consumeUser(followings: List<Following>) {
        when (followings) {
            null -> _errorLiveData.postValue(true)
            else -> _followingLiveData.postValue(followings)
        }
    }
}