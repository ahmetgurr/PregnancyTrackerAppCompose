package com.ahmetgur.pregnancytracker.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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


    @Composable
    fun showLogoutDialog(
        showDialog: MutableState<Boolean>,
        authViewModel: AuthViewModel,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    ) {
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Account")  },
                text = {
                    Text("Are you sure you want to delete your account?\n" +
                            "This is an irreversible process!\n\n" + "Do you confirm that all your data will be deleted?")
                },
                confirmButton = {
                    IconButton(onClick = {
                        authViewModel.deleteAccount()
                        showDialog.value = false
                        onConfirm()
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    IconButton(onClick = {
                        showDialog.value = false
                        onDismiss()
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }


}
