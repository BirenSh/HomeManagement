package com.example.homemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.example.homemanagement.ui.theme.HomeManagementTheme
import com.example.homemanagement.ui.theme.screens.splash_screen.SplashScreen
import com.google.firebase.FirebaseApp
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this) // Initialize Firebase
        setContent {
            HomeManagementTheme {

               Navigator(SplashScreen())

            }
        }
    }
}

