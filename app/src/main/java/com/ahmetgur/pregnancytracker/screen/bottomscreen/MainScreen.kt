package com.ahmetgur.pregnancytracker.screen.bottomscreen

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    onNavigateToNoteScreen: () -> Unit,

    ) {

    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { CalendarView(it) },
            update = {
                it.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar
                }
            })

        Card(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToNoteScreen()
            }
        ) {
            Column(modifier = Modifier.padding(16.dp)){
                Text(text = "Selected Date: ${selectedDate.weekYear}-${selectedDate.get(Calendar.MONTH)+1}-${selectedDate.get(Calendar.DAY_OF_MONTH)}")
                Text(text = "Click to see the details", color = Color.Gray)
                // Firestore'dan notları al ve burada da göster.
            }
        }

    }
}