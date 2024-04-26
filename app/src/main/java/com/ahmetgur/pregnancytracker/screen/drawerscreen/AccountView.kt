package com.ahmetgur.pregnancytracker.screen.drawerscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.data.Baby
import com.ahmetgur.pregnancytracker.util.Util
import com.ahmetgur.pregnancytracker.util.Util.showLogoutDialog
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel
import com.ahmetgur.pregnancytracker.data.Result
import com.ahmetgur.pregnancytracker.data.User
import com.ahmetgur.pregnancytracker.viewmodel.BabyViewModel
import java.util.*

@SuppressLint("SuspiciousIndentation")
@Composable
fun AccountView(
    authViewModel: AuthViewModel,
    babyViewModel: BabyViewModel,
    navController: NavController
) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var dueDate by remember { mutableStateOf("") }
    var babyName by remember { mutableStateOf("") }
    var babyWeight by remember { mutableStateOf("") }
    var babyGender by remember { mutableStateOf("") }
    var babyBirthDate by remember { mutableStateOf("") }

    // Fetch user data when logged in
    LaunchedEffect(key1 = authViewModel.isLoggedIn()) {
        if (authViewModel.isLoggedIn()) {
            authViewModel.getUserData()
        }
    }
    // Fetch baby data when logged in
    LaunchedEffect(key1 = babyViewModel.isLoggedIn) {
        if (babyViewModel.isLoggedIn()) {
            babyViewModel.getBabyData()
        }
    }

    val userData = authViewModel.userData.observeAsState()
    val babyData = babyViewModel.babyData.observeAsState()

    // Update fields when user data is fetched
    LaunchedEffect(key1 = userData.value) {
        when (val result = userData.value) {
            is Result.Success -> {
                val user = result.data
                username = user.username
                email = user.email
                phone = user.phone
                weight = user.weight
                height = user.height
                age = user.age
            }
            is Result.Error -> {
                Log.e("AccountView", "Error getting user data: ${result.exception.message}", result.exception)
            }
            else -> {
                Log.d("AccountView", "Loading user data")}
        }
    }

    // Update fields when baby data is fetched
    LaunchedEffect(key1 = babyData.value) {
        when (val result = babyData.value) {
            is Result.Success -> {
                val baby = result.data
                dueDate = baby.dueDate
                babyName = baby.name
                babyWeight = baby.weight
                babyBirthDate = baby.birthDate
                babyGender = baby.gender
            }
            is Result.Error -> {
                Log.e("AccountView", "Error getting baby data: ${result.exception.message}", result.exception)
            }
            else -> {
                Log.d("AccountView", "Loading baby data")}
        }
    }

    val onUpdateInformationClick: () -> Unit = {
        val updatedUser = User(username, email, phone, weight, height, age)
        authViewModel.updateUserData(updatedUser)
    }

    val onUpdateBabyDataClick: () -> Unit = {
        val updatedBaby = Baby(dueDate = dueDate, name = babyName, weight = babyWeight, birthDate = babyBirthDate, gender = babyGender)
        babyViewModel.updateBabyData(updatedBaby)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AccountCard(username = username)

        Spacer(modifier = Modifier.height(16.dp))

        InformationCard(
            title = "My Health Information",
            username = username,
            email = email,
            phone = phone,
            weight = weight,
            height = height,
            age = age,
            onUsernameChange = { username = it },
            onEmailChange = { email = it },
            onPhoneChange = { phone = it },
            onWeightChange = { weight = it },
            onHeightChange = { height = it },
            onAgeChange = { age = it },
            onUpdateInformationClick = {
                if (username.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) {
                    onUpdateInformationClick()
                    Toast.makeText(context, "Information updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }

        ){
            showDialog.value = true
        }

        Spacer(modifier = Modifier.height(16.dp))

        InformationCardBaby(
            title = "My Baby's Health",
            babyName = babyName,
            babyWeight = babyWeight,
            dueDate = dueDate,
            babyBirthDate = babyBirthDate,
            babyGender = babyGender,
            onBabyNameChange = { babyName = it },
            onBabyWeightChange = { babyWeight = it },
            onDueDateChange = { dueDate = it },
            onBabyBirthDateChange = { babyBirthDate = it },
            onBabyGenderChange = {  },
            onUpdateInformationClick = {
                onUpdateBabyDataClick()
                Toast.makeText(context, "Information updated", Toast.LENGTH_SHORT).show()
                Log.d("onSaveBabyInformation", "Baby information saved CLİCKED")
            }
        )

        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = {},
            content = {
                Text(
                    text = "Add new baby information",
                    color = colorScheme.primary
                )
            },
        )


    }

    showLogoutDialog(
        showDialog = showDialog,
        authViewModel = authViewModel,
        onConfirm = { Util.logoutAndNavigateToLogin(authViewModel, context, navController) },
        onDismiss = {}
    )
}

@Composable
fun AccountCard(
    username: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
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
                text = username,
                textAlign = TextAlign.Center,
                style = typography.headlineMedium,
                color = colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun InformationCard(
    title: String,
    username: String,
    email: String,
    phone: String,
    weight: String,
    height: String,
    age : String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onUpdateInformationClick: () -> Unit,
    onDeleteAccountClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.secondary)) {
                // Başlık
                Text(
                    text = title,
                    style = typography.headlineSmall,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            // Username TextField
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                enabled = true
            )

            // Phone TextField
            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            // Weight TextField
            OutlinedTextField(
                value = weight,
                onValueChange = onWeightChange,
                label = { Text("Pre-pregnancy weight (kg)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Height TextField
            OutlinedTextField(
                value = height,
                onValueChange = onHeightChange,
                label = { Text("Height (cm)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // Age TextField
            OutlinedTextField(
                value = age,
                onValueChange = onAgeChange,
                label = { Text("Your age") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            // Update Information Button
            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = onUpdateInformationClick,
                content = {
                    Text(
                        text = "Update Information",
                        color = colorScheme.primary
                    )
                },
            )

            // Delete Account Button
            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = onDeleteAccountClick,
                content = {
                    Text(
                        text = "Delete Account",
                        color = colorScheme.error
                    )
                },
            )
        }
    }
}

@Composable
fun InformationCardBaby(
    title: String,
    dueDate: String,
    babyName: String,
    babyWeight: String,
    babyBirthDate: String,
    babyGender: String,
    onDueDateChange: (String) -> Unit,
    onBabyNameChange: (String) -> Unit,
    onBabyWeightChange: (String) -> Unit,
    onBabyBirthDateChange: (String) -> Unit,
    onBabyGenderChange: (String) -> Unit,
    onUpdateInformationClick: () -> Unit,
) {

    // Baby Gender Dropdown Menu
    val genderOptions = listOf("♂ Male", "♀ Female", " x Unknown")
    var showGenderPicker by remember { mutableStateOf(false) }
    val selectedGender = remember { mutableStateOf(babyGender) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.secondary)) {
                // Başlık
                Text(
                    text = title,
                    style = typography.headlineSmall,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
            // dueDate TextField
            OutlinedTextField(
                value = dueDate,
                onValueChange = onDueDateChange,
                label = { Text("Due date / weeks pregnant") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            // BabyName TextField
            OutlinedTextField(
                value = babyName,
                onValueChange = onBabyNameChange,
                label = { Text("Baby's nickname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            // babyWeight TextField
            OutlinedTextField(
                value = babyWeight,
                onValueChange = onBabyWeightChange,
                label = { Text("Baby's birth weight (kg)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // babyBirthDate TextField
            OutlinedTextField(
                value = babyBirthDate,
                onValueChange = onBabyBirthDateChange,
                label = { Text("Baby's birth date") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            // babyGender TextField
            OutlinedTextField(
                value = selectedGender.value,
                onValueChange = { selectedGender.value = it },
                label = { Text("Baby's gender") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { showGenderPicker = true },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showGenderPicker = true }) {
                        Icon( contentDescription = "Select Gender", imageVector = Icons.Default.ArrowDropDown)
                    }
                },
            )
            if (showGenderPicker) {
                Dialog(onDismissRequest = { showGenderPicker = false }) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        genderOptions.forEach { gender ->
                            DropdownMenuItem(onClick = {
                                selectedGender.value = gender
                                onBabyGenderChange(gender)
                                showGenderPicker = false
                            }) {
                                Text(text = gender, color = colorScheme.primary, style = typography.headlineLarge, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }

            // Update Information Button
            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = onUpdateInformationClick,
                content = {
                    Text(
                        text = "Update Information",
                        color = colorScheme.primary
                    )
                },
            )
        }
    }
}