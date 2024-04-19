package com.ahmetgur.pregnancytracker.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel,
    selectedDate: String
) {
    var noteText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(8.dp),
            label = { Text("Enter your note") }
        )

        Button(
            onClick = {
                noteViewModel.saveNote(selectedDate, noteText)
                navController.popBackStack()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Save")
        }
    }
}


