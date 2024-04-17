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
            _saveNoteResult.value = notesRepository.saveNote(date, content)
        }
    }

    fun getNotesByDate(date: String) {
        viewModelScope.launch {
            _notesByDateResult.value = notesRepository.getNotesByDate(date)
        }
    }
    fun saveSelectedDateToFirestore(selectedDate: Calendar) {
        viewModelScope.launch {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
            _saveNoteResult.value = notesRepository.saveNote(formattedDate, "")
        }
    }

}
