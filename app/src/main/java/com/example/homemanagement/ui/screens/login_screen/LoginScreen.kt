package com.example.homemanagement.ui.screens.login_screen

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.homemanagement.R
import com.example.homemanagement.ui.screens.first_sync.FirstTimeSync
import com.example.homemanagement.ui.screens.home_screen.HomeScreen
import com.example.homemanagement.ui.screens.splash_screen.SplashScreen

class LoginScreen:Screen {
    @Composable
    override fun Content() {
        LoginScreenUI()
    }

    @Composable
    private fun LoginScreenUI() {

        val navigator:Navigator? = LocalNavigator.current

        var emailId by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val viewmodel = hiltViewModel<LoginViewModel>()

        val uiState by viewmodel.uiState.collectAsState()
        var isRegistering by remember { mutableStateOf(false)}


        Box( modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
           ) {

            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clip(shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                    .background(Color(0xFF7D71FF)) // Purple background,
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.login_boy),
                    contentDescription = "login boy icon"
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF7D71FF)
                        )

                        Spacer(modifier = Modifier.height(16.dp))


                        OutlinedTextField(
                            value = emailId,
                            onValueChange = { emailId = it },
                            label = { Text("Enter valid Email") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Username Icon"
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Password Icon"
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            )


                        Spacer(modifier = Modifier.height(8.dp))

                        if (isRegistering){
                            Button(
                                onClick = {
                                        viewmodel.registerWithEmail(emailId, password)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D71FF))
                            ) {

                                    Text("Register Account", color = Color.White)


                            }
                        }else{
                            Button(
                                onClick = {
                                    viewmodel.loginWithEmailPassword(emailId, password)

                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D71FF))
                            ) {
                                Text("LOGIN", color = Color.White)

                            }
                        }


                        Spacer(modifier = Modifier.height(8.dp))


                        if (isRegistering){
                            TextButton(onClick = { isRegistering = false }) {
                                Text("Go Back", color = Color(0xFF7D71FF))
                            }
                        }else{
                            TextButton(onClick = { isRegistering = true }) {
                                Text("Don't have Account? Register", color = Color(0xFF7D71FF))
                            }
                        }


                    }
                }

                when (uiState) {
                    is AuthState.Loading -> {
                        CircularProgressIndicator()
                        Text("Loading..")
                    }
                    is AuthState.Success ->{
                        val userID = (uiState as AuthState.Success).userId
                        navigator?.push(FirstTimeSync())
                    }
                    is AuthState.Error -> Text("Error: ${(uiState as AuthState.Error).message}", color = MaterialTheme.colorScheme.error)
                    else -> {}
                }
            }

        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun showUI(modifier: Modifier = Modifier) {
        LoginScreenUI()
    }

}

