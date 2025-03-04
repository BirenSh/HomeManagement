package com.example.homemanagement.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData (
    val userName:String ,
    @PrimaryKey
    val userId:String
)