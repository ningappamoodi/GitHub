package com.example.github.di

import androidx.room.Room
import com.example.github.domain.repo.UserDatabase
import com.example.github.domain.repo.UserRepo
import org.koin.core.scope.Scope
import org.koin.dsl.module


val dbModule = module {
    single { provideNewsDatabase() }
    single { userDao() }
    single { UserRepo(get()) }
}

private fun Scope.userDao() = get<UserDatabase>().userDao()

private fun Scope.provideNewsDatabase() =
    Room.databaseBuilder(get(), UserDatabase::class.java, "db-user")
        .fallbackToDestructiveMigration().build()