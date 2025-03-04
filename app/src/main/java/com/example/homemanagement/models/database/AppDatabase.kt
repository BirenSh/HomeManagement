package com.example.homemanagement.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homemanagement.models.data_models.UserEntity
import com.example.homemanagement.models.data_models.UserStatusEntity
import com.example.homemanagement.models.data_models.DutyEntity
import com.example.homemanagement.models.data_models.ReminderEntity
import com.example.homemanagement.models.dao.UserDao
import com.example.homemanagement.models.dao.UserStatusDao
import com.example.homemanagement.models.dao.DutyDao
import com.example.homemanagement.models.dao.ReminderDao

@Database(
    entities = [UserEntity::class, UserStatusEntity::class, DutyEntity::class, ReminderEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userStatusDao(): UserStatusDao
    abstract fun dutyDao(): DutyDao
    abstract fun reminderDao(): ReminderDao
} 