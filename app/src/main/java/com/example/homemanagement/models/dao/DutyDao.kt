package com.example.homemanagement.models.dao

import androidx.room.*
import com.example.homemanagement.models.data_models.DutyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DutyDao {
    @Query("SELECT * FROM duties")
    fun getAllDuties(): Flow<List<DutyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuty(duty: DutyEntity)

    @Delete
    suspend fun deleteDuty(duty: DutyEntity)
}