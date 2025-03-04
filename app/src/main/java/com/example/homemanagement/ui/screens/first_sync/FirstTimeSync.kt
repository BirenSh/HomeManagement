package com.example.homemanagement.ui.screens.first_sync

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.homemanagement.R
import com.example.homemanagement.ui.screens.home_screen.HomeScreen
import com.example.homemanagement.ui.screens.users_screen.UsersViewModel
import kotlinx.coroutines.delay

class FirstTimeSync:Screen {
    @Composable
    override fun Content() {
        val userViewModel = hiltViewModel<UsersViewModel>()
        val syncViewModel = hiltViewModel<FirstTimeSyncViewModel>()

        val navController = LocalNavigator.current
        LaunchedEffect(Unit) {
            syncViewModel.syncUsersFromFireStore()
        }

        LaunchedEffect(Unit) {
            delay(3000)
            navController?.push(HomeScreen())
        }

        FirstTimeSyncUI(userViewModel)
    }

    private @Composable
    fun FirstTimeSyncUI(userViewModel: UsersViewModel) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.height(100.dp)
                        .width(100.dp),
                    painter = painterResource(R.drawable.splash_logo),
                    contentDescription = "splash logo",

                    )

                Text(
                    text = "Home Management",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Syncing Data",
                    style = MaterialTheme.typography.titleSmall
                )

            }

        }

    } //FirstTimeSyncUI



    //class end there
}