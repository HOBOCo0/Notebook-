package com.example.thenotebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenotebook.models.NoteRequest
import com.example.thenotebook.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val notesLiveData get() = noteRepository.notesLiveData
    val statusLiveData get() = noteRepository.statusLiveData

    fun createNote(noteRequest: NoteRequest) {
        viewModelScope.launch {
            noteRepository.createNote(noteRequest)
        }
    }

    fun getNotes() {
        viewModelScope.launch {
            noteRepository.getNotes()
        }
    }

    fun updateNote(id: String, noteRequest: NoteRequest){
        viewModelScope.launch {
            noteRepository.updateNote(id, noteRequest)
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
        }
    }
}