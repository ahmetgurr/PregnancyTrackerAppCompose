package com.ahmetgur.pregnancytracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetgur.pregnancytracker.data.Note
import com.ahmetgur.pregnancytracker.data.NotesRepository
import com.ahmetgur.pregnancytracker.data.Result
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    private val notesRepository: NotesRepository
    private val firestore: FirebaseFirestore

    private val _saveNoteResult = MutableLiveData<Result<Boolean>>()
    val saveNoteResult: LiveData<Result<Boolean>> get() = _saveNoteResult

    private val _notesByDateResult = MutableLiveData<List<Note>>()
    val notesByDateResult: LiveData<List<Note>> get() = _notesByDateResult


    init {
        notesRepository = NotesRepository(FirebaseFirestore.getInstance())
        firestore = FirebaseFirestore.getInstance()
    }

    fun saveNote(date: String, content: String) {
        viewModelScope.launch {
            when (val result = notesRepository.saveNote(date, content)) {
                is Result.Success -> {
                    val savedNote = result.data
                    _saveNoteResult.value = Result.Success(true)
                    Log.d("NoteViewModel", "saveNote: $savedNote")
                }
                is Result.Error -> {
                    Log.d("NoteViewModel", "saveNoteError: ${result.exception.message}")
                }
            }
        }
    }

    fun getNotesByDate(date: String) {
        viewModelScope.launch {
            when (val result = notesRepository.getNotesByDate(date)) {
                is Result.Success -> {
                    val notes = result.data
                    _notesByDateResult.value = notes
                    Log.d("NoteViewModel", "getNotesByDate: $notes")
                }
                is Result.Error -> {
                    Log.d("NoteViewModel", "getNotesByDateError: ${result.exception.message}")
                }
            }
        }
    }
}