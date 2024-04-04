package com.ahmetgur.pregnancytracker.screen.bottomscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahmetgur.pregnancytracker.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(){
    val grouped = listOf<String>("Baby Care","Favorites","Sleep", "Doctor's advice",).groupBy { it[0] }
    val categories = listOf("Baby", "Activity", "Workout", "Medications", "Weight", "Getting Pragnant")

    LazyColumn{
        grouped.forEach{
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow{
                    items(categories){ cat->
                        BrowserItem(cat= cat, drawable = R.drawable.baseline_pregnant_woman_24)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(cat: String, drawable:Int){
    Card(modifier = Modifier
        .padding(16.dp)
        .size(200.dp)
        .clickable {},
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