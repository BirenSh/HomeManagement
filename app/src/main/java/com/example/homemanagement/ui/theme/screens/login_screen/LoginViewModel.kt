package com.example.homemanagement.ui.theme.screens.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemanagement.models.data_models.UserEntity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)
    val uiState: StateFlow<AuthState> = _uiState

    fun validateEmailId(email: String,password: String):Boolean{
          if (email.isEmpty() || password.isEmpty()){
             _uiState.value =  AuthState.Error("Error: Email or Password can not be empty")
              return false
        }
        return true

    }


    fun loginWithEmailPassword(emailId: String, password: String) {
        if ( !validateEmailId(emailId,password)){
         return
        }
        viewModelScope.launch {
            _uiState.value = AuthState.Loading
            firebaseAuth.signInWithEmailAndPassword(emailId, password)
                .addOnSuccessListener { result ->
                    _uiState.value = AuthState.Success(result.user?.uid ?: "")
                }
                .addOnFailureListener { exception ->
                    _uiState.value = AuthState.Error(exception.message ?: "Login Failed")
                }
        }
    }

    fun registerWithEmail(email: String, password: String) {
        if ( !validateEmailId(email,password)){
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthState.Loading
            if (email.isEmpty() || password.isEmpty()){
                _uiState.value = AuthState.Error("Email id or password can not be empty")
                return@launch
            }
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val userId = result.user?.uid?:""
                    _uiState.value = AuthState.Success (userId?:"")

                    // After creating user in FirebaseAuth, save to Firestore
                    // Create a user entity
                    val user = UserEntity(
                        userId = userId,
                        name = email,
                        phoneNumber = "1232323223",
                        role = "Admin"
                    )

//                    firestore.collection("users").document(userId)
//                        .set(user)
//                        .addOnSuccessListener {
//                            _uiState.value = AuthState.Success(userId)
//                        }
//                        .addOnFailureListener { exception ->
//                            _uiState.value = AuthState.Error(exception.message ?: "Failed to store user data")
//                        }


                }
                .addOnFailureListener { exception ->
                    _uiState.value = AuthState.Error(exception.message ?: "Registration Failed")
                }
        }

    }

}