package com.ahmetgur.pregnancytracker.screen.bottomscreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    // ViewModel'dan notları yükle, LiveData'yı State'e çevir
    LaunchedEffect(key1 = selectedDate) {
        val formattedDate = dateFormat.format(selectedDate.time)
        noteViewModel.getNotesByDate(formattedDate)
    }
    val notes = noteViewModel.notesByDateResult.observeAsState(initial = emptyList())



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

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
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
                val noteContent = notes.value.firstOrNull()?.content ?: "No notes for this date."
                Text(text = "Selected Date: ${dateFormat.format(selectedDate.time)}",
                    color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Note: $noteContent")
            }

        }
    }
}