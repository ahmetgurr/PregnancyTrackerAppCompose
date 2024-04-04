package com.ahmetgur.pregnancytracker.screen.drawerscreen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.MainActivity
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.Screen
import com.ahmetgur.pregnancytracker.util.Util.logoutAndNavigateToLogin
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

@Composable
fun AccountView(authViewModel: AuthViewModel, navController: NavController){
    // Kullanıcıdan onay almak için bir MutableState kullanıyoruz
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(){
                Icon(imageVector= Icons.Default.AccountCircle ,
                    contentDescription = "Account",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Column {
                    Text("Ahmet GÜR")
                    Text("@ahmetgur")
                }
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null)
            }
        }

        Row(modifier = Modifier.padding(top = 16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "My Profile",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "My List")
        }
        Divider()

        // Kullanıcıya silme işlemini onaylamak için AlertDialog kullanıyoruz
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Account") },
                text = { Text("Are you sure you want to delete your account?\n" +
                        "This is an irreversible process!\n\n" +"Do you confirm that all your data will be deleted?") },
                confirmButton = {
                    IconButton(onClick = {
                        authViewModel.deleteAccount()
                        showDialog.value = false
                        logoutAndNavigateToLogin(authViewModel, context, navController)
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    IconButton(onClick = {
                        showDialog.value = false
                    }) {
                        Text("No")
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    showDialog.value = true
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_no_accounts_delete_24),
                contentDescription = "Delete Account!",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Delete Account!", color = Color.Red)

        }
        Divider()
    }
}
