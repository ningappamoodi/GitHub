package com.example.github.domain.repo

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.github.domain.entity.User
import kotlinx.coroutines.flow.Flow


interface UserDao {

    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User?)

    @Query("DELETE FROM User where id=:id")
    suspend  fun cleanUser(id: Int)

    @Transaction
    suspend fun deleteAndCreateUser(user: User?) {

        user?.let {
            cleanUser(it.id)
            insertUser(it)
        }
    }

    @Query("SELECT * FROM User where id=:id LIMIT 1")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT * FROM User where login=:username LIMIT 1")
    fun getUser(username: String): Flow<User>
}