package com.ahmetgur.pregnancytracker.screen.bottomscreen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.Screen
import com.ahmetgur.pregnancytracker.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    noteViewModel: NoteViewModel
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
                it.setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar
                    Log.d("MainScreen", "Selected date: ${selectedDate.time}")
                }
            })

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                    navController.navigate(Screen.NoteScreen.route + "/$formattedDate")

                    noteViewModel.getNotesByDate(date = formattedDate)
                    Log.d("MainScreen", "Selected date: $formattedDate")
                }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Selected Date: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)}")
            }
        }
    }
}
