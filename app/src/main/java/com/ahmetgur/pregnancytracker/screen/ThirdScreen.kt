package com.ahmetgur.pregnancytracker.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahmetgur.pregnancytracker.data.Prf
import com.ahmetgur.pregnancytracker.data.profiles

@Composable
fun Profile(){
    LazyColumn(){
        items(profiles){ lib ->
            ProfileItem(prf = lib)
        }
    }
}

@Composable
fun ProfileItem(prf: Prf){
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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