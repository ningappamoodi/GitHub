package com.example.github.ui.followers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.entity.Followers
import com.example.github.domain.usecases.FollowersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowersViewModel(private val followersUseCase: FollowersUseCase): ViewModel() {

    private val _followersLiveData: MutableLiveData<List<Followers>> = MutableLiveData()
    val followersLiveData: LiveData<List<Followers>> = _followersLiveData

    private val _errorLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorLiveData: LiveData<Boolean> = _errorLiveData

    fun fetch(username: String) {
        _errorLiveData.value = false

        viewModelScope.launch {
            try {
              val followers =  followersUseCase.fetchFollowers(username)
                followers.collect { consumeUser(it) }
            } catch (e: Throwable) {
                e.printStackTrace()
                _errorLiveData.postValue(true)
            }

        }
    }

    private fun consumeUser(followers: List<Followers>) {
        when (followers) {
            null -> _errorLiveData.postValue(true)
            else -> _followersLiveData.postValue(followers)
        }
    }
}