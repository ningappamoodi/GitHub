package com.example.github.di

import com.example.github.domain.repo.UserRepo
import org.koin.dsl.module

val repoModule = module{ single {  UserRepo(get()) }
}