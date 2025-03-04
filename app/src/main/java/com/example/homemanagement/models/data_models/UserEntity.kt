package com.example.homemanagement.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val userId: String,
    val name: String,
    val phoneNumber: String,
    val role: String // e.g., Admin, Member
) 