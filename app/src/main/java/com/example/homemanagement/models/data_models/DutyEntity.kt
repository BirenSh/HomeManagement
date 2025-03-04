package com.example.homemanagement.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "duties")
data class DutyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val dutyName: String,
    val status: Boolean = false,  // false = Incomplete, true = Completed
    val lastUpdated: Long // Timestamp
) 