package com.example.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Following(@PrimaryKey val id: Int, var login: String, var username: String,
                     val name: String?,  val avatar_url:String?)
