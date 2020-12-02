package com.example.github.data.apiservices

import com.example.github.domain.entity.Followers
import com.example.github.domain.entity.Following
import com.example.github.domain.entity.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}")
    suspend fun fetchUser(@Path("username") username: String) : User

    @GET("/users/{username}/followers")
    suspend fun fetchFollowers(@Path("username") username: String) : List<Followers>

    @GET("/users/{username}/following")
    suspend fun fetchFollowing(@Path("username") username: String) : List<Following>
}