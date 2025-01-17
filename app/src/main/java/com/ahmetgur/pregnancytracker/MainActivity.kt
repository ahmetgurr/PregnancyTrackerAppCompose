package com.ahmetgur.pregnancytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmetgur.pregnancytracker.data.Category
import com.ahmetgur.pregnancytracker.screen.CategoryDetailScreen
import com.ahmetgur.pregnancytracker.screen.MainView
import com.ahmetgur.pregnancytracker.screen.NoteScreen
import com.ahmetgur.pregnancytracker.screen.TryScreen
import com.ahmetgur.pregnancytracker.screen.bottomscreen.DiscoverScreen
import com.ahmetgur.pregnancytracker.screen.bottomscreen.MainScreen
import com.ahmetgur.pregnancytracker.screen.bottomscreen.Profile
import com.ahmetgur.pregnancytracker.screen.drawerscreen.AccountView
import com.ahmetgur.pregnancytracker.screen.drawerscreen.Premium
import com.ahmetgur.pregnancytracker.screen.login.LoginScreen
import com.ahmetgur.pregnancytracker.screen.login.RegisterScreen
import com.ahmetgur.pregnancytracker.screen.login.ResetScreen
import com.ahmetgur.pregnancytracker.ui.theme.PregnancyTrackerTheme
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel
import com.ahmetgur.pregnancytracker.viewmodel.BabyViewModel
import com.ahmetgur.pregnancytracker.viewmodel.CategoryViewModel
import com.ahmetgur.pregnancytracker.viewmodel.NoteViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()

            PregnancyTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavigationLogin(navController = navController, authViewModel = authViewModel)
                    if (authViewModel.isLoggedIn()) {
                        // Kullanıcı giriş yapmışsa, ana ekrana yönlendir
                        MainView()
                    } else {
                        // Kullanıcı giriş yapmamışsa, giriş ekranına yönlendir
                        NavigationLogin(
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    babyViewModel: BabyViewModel
) {
    val categoryViewModel: CategoryViewModel = viewModel()
    val viewstate by categoryViewModel.categoriesState

    NavHost(
        navController = navController,
        startDestination = Screen.BottomScreen.MainScreen.route,
        modifier = Modifier.fillMaxSize().padding(bottom = 56.dp)
    ) {
        // Main Screen
        composable(Screen.BottomScreen.MainScreen.route) {
            val noteViewModel: NoteViewModel = viewModel()
            MainScreen(
                navController = navController,
                noteViewModel = noteViewModel
            )
        }

        //NoteScreen
        composable(
            route = Screen.NoteScreen.route + "/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) { backStackEntry ->
            NoteScreen(
                navController = navController,
                noteViewModel = viewModel(),
                selectedDate = backStackEntry.arguments?.getString("date") ?: ""
            )
        }

        // Discover Screen
        composable(Screen.BottomScreen.Discover.route) {
            DiscoverScreen(viewstate = viewstate, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        // Discover Detail Screen
        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }

        // Library Screen
        composable(Screen.BottomScreen.Profile.route) {
            Profile(authViewModel = authViewModel, navController = navController)
        }

        // Account View Screen
        composable(Screen.DrawerScreen.Account.route) {
            AccountView(authViewModel = authViewModel, babyViewModel = babyViewModel, navController = navController)
        }

        // Subscription Screen
        composable(Screen.DrawerScreen.Premium.route) {
            Premium()
        }

        composable(Screen.LoginProceduresScreen.MainView.route) {
            MainView()
        }
        composable(Screen.LoginProceduresScreen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.LoginProceduresScreen.Register.route) },
                onNavigateToReset = { navController.navigate(Screen.LoginProceduresScreen.Reset.route) },
                onSignInSuccess = { navController.navigate(Screen.LoginProceduresScreen.MainView.route) }
            )
        }

        composable(Screen.TryScreen.route) {
            TryScreen(
                authViewModel = authViewModel,
                navController = navController
            )
        }

    }
}



@Composable
fun NavigationLogin(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.LoginProceduresScreen.Login.route,
        modifier = Modifier.fillMaxSize()
    ) {
        // Login Screen
        composable(Screen.LoginProceduresScreen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.LoginProceduresScreen.Register.route) },
                onNavigateToReset = { navController.navigate(Screen.LoginProceduresScreen.Reset.route) },
                onSignInSuccess = { navController.navigate(Screen.LoginProceduresScreen.MainView.route) }
            )
        }

        // Register Screen
        composable(Screen.LoginProceduresScreen.Register.route) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Login.route) },
                onRegisterInSuccess = { navController.navigate(Screen.LoginProceduresScreen.MainView.route) }
            )
        }

        // Reset Screen
        composable(Screen.LoginProceduresScreen.Reset.route) {
            ResetScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginProceduresScreen.Login.route) }
            )
        }
        composable(Screen.LoginProceduresScreen.MainView.route) {
            MainView()
        }

    }
}