package com.ahmetgur.pregnancytracker.screen

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun CustomAlertDialog(
    dialogOpen: MutableState<Boolean>,
    title: String,
    message: String,
) {
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogOpen.value = false },
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { dialogOpen.value = false }) {
                    Text("OK")
                }
            },
        )
    }
}

