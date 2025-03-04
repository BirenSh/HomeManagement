package com.example.homemanagement.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val dutyId: Int,
    val reminderTime: Long, // Timestamp
    val isCompleted: Boolean = false
) 