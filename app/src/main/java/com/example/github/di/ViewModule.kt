package com.example.github.di

import com.example.github.ui.followers.viewmodel.FollowersViewModel
import com.example.github.ui.home.HomeViewModel
import com.example.github.ui.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {
    viewModel { HomeViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
}