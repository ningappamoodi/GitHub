package com.example.github

import android.app.Application
import com.example.github.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@GitHubApp)
            modules(viewModule, useCaseModule, dataSourceModule, dbModule, networkModule) }
    }
}