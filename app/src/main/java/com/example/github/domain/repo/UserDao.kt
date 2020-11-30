package com.example.github.domain.repo

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.github.domain.entity.User
import kotlinx.coroutines.flow.Flow


interface UserDao {

    @Query("SELECT * FROM User")
    fun getUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(articles: List<User>?)

    @Query("DELETE FROM User")
    suspend  fun cleanUser()

    @Transaction
    suspend fun deleteAndCreateUser(headLinesList: List<User>?) {
        cleanUser()
        headLinesList?.let {  insertUser(it) }
    }

    @Query("SELECT * FROM User where uid=:uid LIMIT 1")
    fun getArticle(uid: Int): User
}