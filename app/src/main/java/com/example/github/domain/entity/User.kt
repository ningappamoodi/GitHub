package com.example.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val id: Int, val login: String, val name: String?,
                val company: String?, val email: String?, val location: String?,
                val bio: String?, val twitter_username: String?, val public_repos: Int,
                val followers: Int, val following: Int, val avatar_url:String?,
                val created_at: String, val updated_at: String)