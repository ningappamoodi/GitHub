package com.example.github.di

import com.example.github.domain.usecases.UserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserUseCase(get()) }
}