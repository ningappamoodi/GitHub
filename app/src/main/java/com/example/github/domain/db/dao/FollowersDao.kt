package com.example.github.domain.db.dao

import androidx.room.*
import com.example.github.domain.entity.Followers
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowersDao {

    @Query("SELECT * FROM Followers where username=:username")
    fun getFollowers(username: String): Flow<List<Followers>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowers(followersList: List<Followers>?)

    @Query("DELETE FROM Followers where username=:username")
    suspend  fun clearFollowers(username: String)

    @Transaction
    suspend fun deleteAndCreateFollowers(username: String, followersList: List<Followers>) {

        clearFollowers(username)
        insertFollowers(followersList)
    }

}