package com.ahmetgur.pregnancytracker.screen.drawerscreen

import android.app.Activity
import android.text.InputType.TYPE_CLASS_PHONE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.util.Util
import com.ahmetgur.pregnancytracker.util.Util.showLogoutDialog
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel


@Composable
fun AccountView(
    authViewModel: AuthViewModel,
    navController: NavController
) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AccountCard()

        Spacer(modifier = Modifier.height(16.dp))

        InformationCard(
            username = username,
            email = email,
            phone = phone,
            weight = weight,
            height = height,
            onUsernameChange = { username = it },
            onEmailChange = { email = it },
            onPhoneChange = { phone = it },
            onWeightChange = { weight = it },
            onHeightChange = { height = it },
            onUpdateInformationClick = { //TODO: updateInformationInFirestore(username, email, phone)
            }
        ){
            showDialog.value = true
        }

        Spacer(modifier = Modifier.height(16.dp))

        AccountCard()
    }

    showLogoutDialog(
        showDialog = showDialog,
        authViewModel = authViewModel,
        onConfirm = { Util.logoutAndNavigateToLogin(authViewModel, context, navController) },
        onDismiss = {}
    )
}

@Composable
fun AccountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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

@Composable
fun InformationCard(
    username: String,
    email: String,
    phone: String,
    weight: String,
    height: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onUpdateInformationClick: () -> Unit,
    onDeleteAccountClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                enabled = false
            )

            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            OutlinedTextField(
                value = weight,
                onValueChange = onWeightChange,
                label = { Text("Pre-pregnancy weight (kg)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = height,
                onValueChange = onHeightChange,
                label = { Text("Height (cm)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = onUpdateInformationClick,
                content = {
                    Text(
                        text = "Update Information",
                    )
                },
            )

            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = onDeleteAccountClick,
                content = {
                    Text(
                        text = "Delete Account",
                        color = MaterialTheme.colorScheme.error
                    )
                },
            )
        }
    }
}
