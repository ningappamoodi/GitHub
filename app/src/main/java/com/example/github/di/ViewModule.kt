package com.example.github.di

import com.example.github.ui.profile.viewmodel.ProfileSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule: Module = module {
    viewModel { ProfileSearchViewModel() }
}