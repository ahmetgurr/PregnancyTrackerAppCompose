package com.ahmetgur.pregnancytracker.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.MainActivity
import com.ahmetgur.pregnancytracker.Screen
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

object Util {
    fun logoutAndNavigateToLogin(
        authViewModel: AuthViewModel,
        context: Context,
        navController: NavController
    ) {
        authViewModel.logout()
        val intent = Intent(context, MainActivity::class.java)
        ContextCompat.startActivity(context, intent, null)
        (context as Activity).finish()
        navController.navigate(Screen.LoginProceduresScreen.Login.route)
    }
}
