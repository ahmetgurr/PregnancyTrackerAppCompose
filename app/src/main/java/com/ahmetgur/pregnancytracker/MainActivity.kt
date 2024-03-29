package com.ahmetgur.pregnancytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmetgur.pregnancytracker.ui.theme.PregnancyTrackerTheme
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel
import com.ahmetgur.pregnancytracker.screen.LoginScreen
import com.ahmetgur.pregnancytracker.screen.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()

            PregnancyTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController = navController, authViewModel = authViewModel)

                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.RegisterScreen.route){

        composable(Screen.RegisterScreen.route){
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.LoginScreen.route)
                })
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.RegisterScreen.route) }
            ){
                navController.navigate(Screen.RegisterScreen.route)
            }
        }

    }
}

/*

// daha anlaşışır kod -  composable(Screen.LoginScreen.route) için geçerli dene
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignupScreen.route)
                },
                onSignInSuccess = { navController.navigate(Screen.ChatScreen.route) }
            )
        }

 */