package com.example.homemanagement.ui.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.homemanagement.R
import com.example.homemanagement.ui.screens.home_screen.HomeScreen
import com.example.homemanagement.ui.screens.login_screen.LoginScreen


class SplashScreen() :Screen {
    @Composable
    override fun Content() {
        SplashScreenUi()
    }

    private @Composable
    fun SplashScreenUi() {
        val navigator:Navigator? = LocalNavigator.current
        val viewModel = hiltViewModel<SplashViewModel>()
        val loading:Boolean by viewModel.loading.collectAsState()
        if (loading){
            ShowSplashUI()
        }else{
            navigator?.push(LoginScreen())
        }

    }

    private @Composable
    fun ShowSplashUI() {
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
                    text = "This is SplashScreen",
                    style = MaterialTheme.typography.titleSmall
                )

            }

        }
    }


    @Preview(showSystemUi = true)
    @Composable
    fun ContentScreen(){
        SplashScreenUi()
    }
}