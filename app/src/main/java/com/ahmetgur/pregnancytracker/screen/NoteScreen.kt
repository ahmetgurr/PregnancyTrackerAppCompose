package com.ahmetgur.pregnancytracker.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetgur.pregnancytracker.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel,
    selectedDate: String
) {
    // ViewModel'dan notları al, LiveData'yı State'e çevir, notes State'i güncellendiğinde noteText'i güncelle
    LaunchedEffect(key1 = selectedDate) {
        noteViewModel.getNotesByDate(selectedDate)
    }
    val notes = noteViewModel.notesByDateResult.observeAsState(initial = emptyList())
    var noteText by remember { mutableStateOf("") }

    LaunchedEffect(notes.value) {
        noteText = notes.value.firstOrNull()?.content ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = noteText,
            onValueChange = { notes -> noteText = notes},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 600.dp)
                .padding(8.dp),
            label = { Text("Enter your note") }
        )

        Row {
            Button(
                onClick = {
                    noteViewModel.deleteNoteById(selectedDate)
                    navController.popBackStack()
                    Toast.makeText(navController.context, "Note deleted", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Delete")
            }

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    noteViewModel.saveNote(selectedDate, noteText)
                    navController.popBackStack()
                    Toast.makeText(navController.context, "Note saved", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Save")
            }

        }

    }
}