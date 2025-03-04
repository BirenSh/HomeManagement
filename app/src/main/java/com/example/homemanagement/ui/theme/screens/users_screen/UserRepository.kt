package com.example.homemanagement.ui.theme.screens.users_screen

import com.example.homemanagement.models.dao.UserDao
import com.example.homemanagement.models.data_models.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    suspend fun addUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun removeUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

//    suspend fun getUserById(userId: Long): UserEntity? {
//        return userDao.getUserById(userId)
//    }
}