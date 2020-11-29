package com.example.github.data.apiservices

import com.example.github.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}")
    suspend fun fetchUser(@Path("username") username: String) : User
}