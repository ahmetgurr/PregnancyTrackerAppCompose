package com.ahmetgur.pregnancytracker.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.util.Util
import com.ahmetgur.pregnancytracker.util.Util.showLogoutDialog
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
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = "Account",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Username",
                    textAlign = TextAlign.Center,
                    style = typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Username",
            textAlign = TextAlign.Center,
            style = typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
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

        Button(onClick = {
            //TODO: updateInformationInFirestore(username, email, phone)
        }) {
            Text("Update Information")
        }

        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = { showDialog.value = true },
            content = {
                Text(
                    text= "Delete Account",
                    color = MaterialTheme.colorScheme.error
                )},
            )
        showLogoutDialog(
            showDialog = showDialog,
            authViewModel = authViewModel,
            onConfirm = { Util.logoutAndNavigateToLogin(authViewModel, context, navController) },
            onDismiss = {}
        )

    }

}

@Composable
@Preview
fun TryScreenPreview() {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "Account",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Username",
                textAlign = TextAlign.Center,
                style = typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
