package com.ahmetgur.pregnancytracker.data

import com.ahmetgur.pregnancytracker.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotesRepository(private val firestore: FirebaseFirestore) {

    suspend fun saveNote(date: String, content: String): Result<Note> =
        try {
            val note = Note(date = date, content = content) // Create a note object without providing id
            val documentReference = firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .document()

            // Set the note object directly to Firestore and use the document ID as the note's ID
            documentReference.set(note.copy(id = documentReference.id)).await()

            Result.Success(note.copy(id = documentReference.id)) // Return the note with the correct ID
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
