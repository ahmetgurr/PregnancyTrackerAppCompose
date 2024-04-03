package com.ahmetgur.pregnancytracker.screen.bottomscreen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.ahmetgur.pregnancytracker.Screen
import com.ahmetgur.pregnancytracker.data.Prf
import com.ahmetgur.pregnancytracker.data.profiles
import com.ahmetgur.pregnancytracker.screen.CustomAlertDialog
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

@Composable
fun Profile(authViewModel: AuthViewModel, navController: NavController,onLogoutClick: () -> Unit = {}){

    val context = LocalContext.current as Activity

    LazyColumn(){
        items(profiles){ prf ->
            ProfileItem(
                prf = prf,
                onLogoutClick = {
                    if (prf.name == "Logout") {
                        authViewModel.logout()
                        // MainActivity'yi başlatma ve mevcut aktiviteyi sonlandırma
                        val intent = Intent(context, MainActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                        (context as Activity).finish()
                        navController.navigate(Screen.LoginProceduresScreen.Login.route)
                    }
                },
                )

            }
        }
    }


@Composable
fun ProfileItem(
    prf: Prf,
    onLogoutClick: () -> Unit = {}
) {
    val showDialog = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    fun showDialog(titleValue: String, messageValue: String) {
        title.value = titleValue
        message.value = messageValue
        showDialog.value = true
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable {
                    when (prf.name) {
                        "Settings" -> {
                        }
                        "About Us" -> {
                            showDialog(
                                titleValue = "About Us",
                                messageValue = "This is the About Us message."
                            )
                        }
                        "Privacy And Policy" -> {
                            showDialog(
                                titleValue = "Privacy And Policy",
                                messageValue = "This is the Privacy And Policy message."
                            )
                        }
                        "Terms And Conditions" -> {
                            showDialog(
                                titleValue = "Terms And Conditions",
                                messageValue = "This is the Terms And Conditions message."
                            )
                        }
                        "Contact Us" -> {
                            showDialog(
                                titleValue = "Contact Us",
                                messageValue = "This is the Contact Us message."
                            )
                        }
                        "Rate Us" -> {
                            showDialog(
                                titleValue = "Rate Us",
                                messageValue = "This is the Rate Us message."
                            )
                        }
                        "Share" -> {
                            showDialog(
                                titleValue = "Share",
                                messageValue = "This is the Share message."
                            )
                        }
                        "Logout" -> {
                            onLogoutClick()
                        }

                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = prf.icon),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    contentDescription = prf.name
                )
                Text(text = prf.name)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow Right")
        }
        Divider(color = Color.LightGray)

        CustomAlertDialog(dialogOpen = showDialog, title = title.value, message = message.value)

    }
}



