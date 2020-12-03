package com.example.github

import com.example.github.domain.entity.Followers
import com.example.github.domain.entity.Following
import com.example.github.domain.entity.User

object DataUtils {

    fun getUsers(): List<User> =  listOf(getUser())

    fun getUser() : User = User(1, "octocat", "Octo Cat", "@Github",
        "name@github.com", "Bengaluru", "Full Stack developer",
        "@github", 8, 10, 15,
        "https://avatars/1", "23-02-20", "23-11-20")

    fun getFollowers(): List<Followers> =  listOf(getFollower())

    fun getFollower(): Followers = Followers(1, "octocat",
        "", "Octo Cat", "https://avatars/1")

    fun getFollowing(): Following = Following(1, "octocat",
        "", "Octo Cat", "https://avatars/1")

    fun getFollowings(): List<Following> = listOf(getFollowing())
}