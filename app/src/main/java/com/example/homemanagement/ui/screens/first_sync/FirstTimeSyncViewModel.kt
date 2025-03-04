package com.example.homemanagement.ui.screens.first_sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemanagement.models.data_models.UserEntity
import com.example.homemanagement.ui.screens.users_screen.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstTimeSyncViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val userRepository: UserRepository
) :ViewModel() {

    fun syncUsersFromFireStore(){
        firestore.collection("users").get()
            .addOnSuccessListener{document->
                val userList = document.map {doc->
                    UserEntity(
                        userId = doc.id,
                        name = doc.getString("name")?:"NA",
                        phoneNumber = doc.getString("phoneNumber") ?: "NA",
                        role = doc.getString("role") ?: "Member"
                    )
                }
                viewModelScope.launch {
                    // TODO: refactor while registering do not add same user to DB
                    userRepository.addListOfUsers(userList)
                }
            }
            .addOnFailureListener {
               println("===failed to save firebase: $it")
            }
    }


}