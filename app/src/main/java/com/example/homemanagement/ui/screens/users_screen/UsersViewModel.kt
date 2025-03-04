package com.example.homemanagement.ui.screens.users_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemanagement.models.data_models.UserEntity
import com.example.homemanagement.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<UserEntity>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<UserEntity>>> = _uiState.asStateFlow()


    val listOfuser = arrayListOf(
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin"),
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin"),
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin"),
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin"),
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin"),
        UserEntity(userId = "123", name = "Birnd", phoneNumber = "23423523", role = "Admin")
    )

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            try {
                repository.addUser(user)
                // Refresh the list after adding
                getUsers()
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to add user: ${e.message}")
            }
        }
    }

    fun removeUser(user: UserEntity) {
        viewModelScope.launch {
            try {
                repository.removeUser(user)
                // Refresh the list after removing
                getUsers()
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to remove user: ${e.message}")
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val userList = repository.getAllUsers()
            if (userList.isNotEmpty()){
                _uiState.value = UiState.Success(userList)
            }else{
                _uiState.value = UiState.Empty
            }

        }
    }
} 