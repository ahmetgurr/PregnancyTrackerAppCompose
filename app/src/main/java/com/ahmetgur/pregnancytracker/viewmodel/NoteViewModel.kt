package com.ahmetgur.pregnancytracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetgur.pregnancytracker.data.Note
import com.ahmetgur.pregnancytracker.data.NotesRepository
import com.ahmetgur.pregnancytracker.data.Result
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteViewModel : ViewModel() {
    private val notesRepository: NotesRepository
    private val firestore: FirebaseFirestore

    private val _saveNoteResult = MutableLiveData<Result<Boolean>>()
    val saveNoteResult: LiveData<Result<Boolean>> get() = _saveNoteResult

    private val _notesByDateResult = MutableLiveData<Result<List<Note>>>()
    val notesByDateResult: LiveData<Result<List<Note>>> get() = _notesByDateResult

    init {
        notesRepository = NotesRepository(FirebaseFirestore.getInstance())
        firestore = FirebaseFirestore.getInstance()
    }

    fun saveNote(date: String, content: String) {
        viewModelScope.launch {
            when (val result = notesRepository.saveNote(date, content)) {
                is Result.Success -> {
                    val savedNote = result.data
                    // Now you have the saved note with the ID included
                    // You can perform any additional actions here if needed
                }
                is Result.Error -> {
                    // Handle error
                }
            }
        }
    }





    fun getNotesByDate(date: String) {
        viewModelScope.launch {
            _notesByDateResult.value = notesRepository.getNotesByDate(date)
        }
    }

}
