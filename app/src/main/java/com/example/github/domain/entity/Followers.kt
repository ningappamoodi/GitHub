package com.example.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Followers(@PrimaryKey val id: Int, val login: String, val name: String?)

