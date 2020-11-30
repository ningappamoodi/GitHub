package com.example.github.di

import com.example.github.domain.datasource.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module{ single {  UserDataSource(get()) }
}