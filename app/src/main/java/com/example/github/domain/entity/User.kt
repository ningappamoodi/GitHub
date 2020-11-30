package com.example.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val id: Int, val name: String, var email: String, var location: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
