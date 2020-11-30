package com.example.github.di

import com.example.github.data.apiservices.GithubService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

val networkModule = module {
    single { provideRetrofit() }
    factory { provideGithubService(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build() }

fun  provideGithubService(retrofit: Retrofit) : GithubService {
    return retrofit.create(GithubService::class.java)
}