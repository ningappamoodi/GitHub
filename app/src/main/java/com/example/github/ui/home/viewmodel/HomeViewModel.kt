package com.example.github.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private var _hideFabLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    set(value) {}

    val hideFabLiveData: LiveData<Boolean> = _hideFabLiveData

    fun hideFab(hide: Boolean) = _hideFabLiveData.postValue(hide)
}