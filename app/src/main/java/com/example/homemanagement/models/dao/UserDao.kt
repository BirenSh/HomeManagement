package com.example.homemanagement.models.dao

import androidx.room.*
import com.example.homemanagement.models.data_models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(user: List<UserEntity>)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}