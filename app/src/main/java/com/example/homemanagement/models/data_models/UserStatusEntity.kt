package com.example.homemanagement.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_status")
data class UserStatusEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val checkInTime: Long?,  // Timestamp
    val checkOutTime: Long?, // Timestamp
    val expectedArrivalTime: Long? // Timestamp
) 