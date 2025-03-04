package com.example.homemanagement.models.dao

import androidx.room.*
import com.example.homemanagement.models.data_models.UserStatusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatusDao {
    @Query("SELECT * FROM user_status")
    fun getAllUserStatuses(): Flow<List<UserStatusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStatus(userStatus: UserStatusEntity)

    @Delete
    suspend fun deleteUserStatus(userStatus: UserStatusEntity)
} 