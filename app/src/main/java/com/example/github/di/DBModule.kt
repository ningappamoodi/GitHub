package com.example.github.di

import androidx.room.Room
import com.example.github.domain.db.UserDatabase
import com.example.github.domain.db.repo.FollowersRepo
import com.example.github.domain.db.repo.FollowingRepo
import com.example.github.domain.db.repo.UserRepo
import org.koin.core.scope.Scope
import org.koin.dsl.module


val dbModule = module {
    single { provideNewsDatabase() }

    single { userDao() }
    single { UserRepo(get()) }

    single { followersDao() }
    single { FollowersRepo(get()) }

    single { followingDao() }
    single { FollowingRepo(get()) }
}

private fun Scope.userDao() = get<UserDatabase>().userDao()

private fun Scope.followersDao() = get<UserDatabase>().followersDao()

private fun Scope.followingDao() = get<UserDatabase>().followingDao()

private fun Scope.provideNewsDatabase() =
    Room.databaseBuilder(get(), UserDatabase::class.java, "db-user")
        .fallbackToDestructiveMigration().build()