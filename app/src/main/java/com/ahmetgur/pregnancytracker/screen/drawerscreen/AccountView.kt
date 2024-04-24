package com.ahmetgur.pregnancytracker.screen.drawerscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.R
import com.ahmetgur.pregnancytracker.util.Util.logoutAndNavigateToLogin
import com.ahmetgur.pregnancytracker.util.Util.showLogoutDialog
import com.ahmetgur.pregnancytracker.viewmodel.AuthViewModel

@Composable
fun AccountView(authViewModel: AuthViewModel, navController: NavController){
    // Kullanıcıdan onay almak için bir MutableState kullanıyoruz
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

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

        // Logout dialog'u göstermek için tıklandığında showDialog state'ini güncelle
        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = { showDialog.value = true },
            content = {
                Text("Delete Account", color = MaterialTheme.colorScheme.error)
            },

        )
        // Utils'den gelen Composable fonksiyonu kullan
        showLogoutDialog(
            showDialog = showDialog,
            authViewModel = authViewModel,
            onConfirm = { logoutAndNavigateToLogin(authViewModel, context, navController) },
            onDismiss = {}
        )


    }

}
