package com.ahmetgur.pregnancytracker.data

import com.ahmetgur.pregnancytracker.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotesRepository(private val firestore: FirebaseFirestore) {

    suspend fun saveNote(date: String, content: String): Result<Boolean> =
        try {
            val note = Note(date, content)
            firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .add(note)
                .await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun getNotesByDate(date: String): Result<List<Note>> =
        try {
            val querySnapshot = firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .whereEqualTo("date", date)
                .get()
                .await()

            val notes = mutableListOf<Note>()
            for (document in querySnapshot.documents) {
                val note = document.toObject(Note::class.java)
                note?.let { notes.add(it) }
            }
            Result.Success(notes)
        } catch (e: Exception) {
            Result.Error(e)
        }
}
