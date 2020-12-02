package com.example.github.domain.db.dao

import androidx.room.*
import com.example.github.domain.entity.Following
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowingDao {
    @Query("SELECT * FROM Following where username=:username")
    fun getFollowing(username: String): Flow<List<Following>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(followers: List<Following>?)

    @Query("DELETE FROM Following where username=:username")
    suspend  fun clearFollowing(username: String?)

    @Transaction
    suspend fun deleteAndCreateFollowings(username: String, following: List<Following>?) {

        following?.let {
            clearFollowing(username)
            insertFollowing(it)
        }
    }
}