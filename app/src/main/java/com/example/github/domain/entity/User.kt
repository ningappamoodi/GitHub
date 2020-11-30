package com.example.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User( @PrimaryKey val id: Int, val login: String, var email: String, var location: String)