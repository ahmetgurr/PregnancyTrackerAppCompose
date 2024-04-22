package com.ahmetgur.pregnancytracker.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.util.Util
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

@Composable
fun TryScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {

    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Username",
            textAlign = TextAlign.Center,
            style = typography.h4,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = false
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Divider()

        Button(onClick = {
            //TODO: updateInformationInFirestore(username, email, phone)
        }) {
            Text("Update Information")
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
                        Util.logoutAndNavigateToLogin(authViewModel, context, navController)
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