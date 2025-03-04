package com.example.homemanagement.ui.screens.login_screen

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success (val userId:String): AuthState()
    data class Error(val message: String) : AuthState()
}

