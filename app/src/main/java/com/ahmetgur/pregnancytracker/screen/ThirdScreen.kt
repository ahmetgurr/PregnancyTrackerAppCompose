package com.ahmetgur.pregnancytracker.screen

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
){
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable { if (prf.name =="Logout") onLogoutClick()  },
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Icon(painter = painterResource(id = prf.icon), modifier =
                Modifier.padding(horizontal = 8.dp), contentDescription = prf.name)
                Text(text = prf.name)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow Right")

        }
        Divider(color = Color.LightGray)
    }
}