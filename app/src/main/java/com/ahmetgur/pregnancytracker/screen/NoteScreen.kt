package com.ahmetgur.pregnancytracker.screen

import java.util.Calendar
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
import com.ahmetgur.pregnancytracker.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
    selectedDate: Calendar,
    onNoteSaved: () -> Unit,
    onCancel: () -> Unit
) {
    var noteText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Not alanı
        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            label = { Text("Enter your note") }
        )

        // Butonlar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    viewModel.saveNote(
                        date = selectedDate.weekYear.toString() + "-" + (selectedDate.get(Calendar.MONTH) + 1) + "-" + selectedDate.get(Calendar.DAY_OF_MONTH),
                        content = noteText
                    )
                    onNoteSaved()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Save")
            }
        }
    }
}
