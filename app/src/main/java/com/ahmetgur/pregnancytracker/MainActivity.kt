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
import com.ahmetgur.pregnancytracker.screen.AccountView
import com.ahmetgur.pregnancytracker.screen.Browse
import com.ahmetgur.pregnancytracker.screen.Library
import com.ahmetgur.pregnancytracker.ui.theme.PregnancyTrackerTheme
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel
import com.ahmetgur.pregnancytracker.screen.MainScreen
import com.ahmetgur.pregnancytracker.screen.MainView
import com.ahmetgur.pregnancytracker.screen.Subscription
import com.ahmetgur.pregnancytracker.screen.login.LoginScreen
import com.ahmetgur.pregnancytracker.screen.login.RegisterScreen
import com.ahmetgur.pregnancytracker.screen.login.ResetScreen


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
                    NavigationLogin(navController = navController, authViewModel = authViewModel)

                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.BottomScreen.MainScreen.route, modifier = Modifier.fillMaxSize()) {

        // Main Screen
        composable(Screen.BottomScreen.MainScreen.route) {
            MainScreen()
        }

        // Browse Screen
        composable(Screen.BottomScreen.Browse.route) {
            Browse()
        }

        // Library Screen
        composable(Screen.BottomScreen.Library.route) {
            Library()
        }

        // Account View Screen
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }

        // Subscription Screen
        composable(Screen.DrawerScreen.Subscription.route) {
            Subscription()
        }

        composable(Screen.LoginProceduresScreen.MainView.route){
            MainView()
        }

    }
}


@Composable
fun NavigationLogin(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.LoginProceduresScreen.Login.route, modifier = Modifier.fillMaxSize()) {
        // Login Screen
        composable(Screen.LoginProceduresScreen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.LoginProceduresScreen.Register.route) },
                onNavigateToReset = { navController.navigate(Screen.LoginProceduresScreen.Reset.route) }
            ) {
                navController.navigate(Screen.LoginProceduresScreen.MainView.route)
            }
        }

        // Register Screen
        composable(Screen.LoginProceduresScreen.Register.route) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Login.route) }
            )
        }

        // Reset Screen
        composable(Screen.LoginProceduresScreen.Reset.route) {
            ResetScreen(
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Login.route) }
            )
        }
        composable(Screen.LoginProceduresScreen.MainView.route){
            MainView()
        }


    }
}




/*
@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.LoginProceduresScreen.Login.route){

        composable(Screen.LoginProceduresScreen.Register.lRoute){
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Login.lRoute)}
            )
        }
        composable(Screen.LoginProceduresScreen.Login.lRoute){
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.LoginProceduresScreen.Register.lRoute) },
                onNavigateToReset = { navController.navigate(Screen.LoginProceduresScreen.Reset.lRoute) }
            ){
                navController.navigate(Screen.BottomScreen.MainScreen.bRoute)
            }
        }
        composable(Screen.LoginProceduresScreen.Reset.lRoute){
            ResetScreen(
                //authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Register.lRoute) }
            )
        }
        composable(Screen.BottomScreen.MainScreen.bRoute){
            MainScreen()
        }


        composable(Screen.BottomScreen.Browse.bRoute){
            Browse()
        }
        composable(Screen.BottomScreen.Library.bRoute){
            Library()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            Subscription()
        }



    }
}

 */