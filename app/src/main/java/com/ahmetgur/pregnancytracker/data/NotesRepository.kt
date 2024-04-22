package com.ahmetgur.pregnancytracker.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class NotesRepository(private val firestore: FirebaseFirestore) {

    suspend fun saveNote(date: String, content: String): Result<Note> =
        try {
            val note = Note(id = date, date = date, content = content) // Tarih bilgisini ID olarak ata
            val documentReference = firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .document(date) // Tarih bilgisini document ID olarak ata

            documentReference.set(note).await()

            Result.Success(note)
        } catch (e: Exception) {
            Result.Error(e)
        }

    // NoteScreen sayfasına iletilen tarih bilgisine göre notları getirmek için
    suspend fun getNotesByDate(date: String): Result<List<Note>> =
        try {
            val querySnapshot = firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .whereEqualTo("date", date)
                .get()
                .await()

            val notes = querySnapshot.documents.mapNotNull { document ->
                document.toObject(Note::class.java)
            }
            Log.d("NotesRepository", "getNotesByDateSucces: $notes")
            Result.Success(notes)
        } catch (e: Exception) {
            Log.d("NotesRepository", "getNotesByDateError: ${e.message}")
            Result.Error(e)

        }

    // Not silme işlemi
    suspend fun deleteNoteById(noteId: String): Result<Boolean> =
        try {
            firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.email ?: "")
                .collection("notes")
                .document(noteId)
                .delete()
                .await()

            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}
