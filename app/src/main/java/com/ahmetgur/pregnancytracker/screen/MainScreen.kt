package com.ahmetgur.pregnancytracker.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.ahmetgur.pregnancytracker.MainActivity
import com.ahmetgur.pregnancytracker.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit
){
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")
    val grouped = listOf<String>("New Release","Favorites","Top  Rated").groupBy { it[0] }

    val context = androidx.compose.ui.platform.LocalContext.current
    var x =  context as Activity

    LazyColumn{
        grouped.forEach{
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow{
                    items(categories){ cat->
                        BrowserItem(cat= cat, drawable = R.drawable.baseline_home_filled_24)
                    }
                }
            }
        }
    }
    Button(
        onClick = {
            onLogout()
            // Giriş ekranına yönlendirme işlemi
            val intent = Intent(context, MainActivity::class.java)
            startActivity(context, intent, null)

            // Mevcut aktiviteyi sonlandırma
            x.finish()


        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Logout")
    }
}


@Composable
fun BrowserItem(cat: String, drawable:Int){
    Card(modifier = Modifier
        .padding(16.dp)
        .size(200.dp),
        border = BorderStroke(3.dp, color = Color.DarkGray)
    ){
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = cat)
            Image(painter = painterResource(id = drawable), contentDescription = cat)
        }
    }
}