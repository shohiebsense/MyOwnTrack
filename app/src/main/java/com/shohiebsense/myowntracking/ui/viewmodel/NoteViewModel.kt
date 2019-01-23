package com.shohiebsense.myowntracking.ui.viewmodel

import android.content.Context
import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.shohiebsense.myowntracking.db.repository.NoteRepository
import com.shohiebsense.myowntracking.model.Note
import com.shohiebsense.myowntracking.utils.Injection

open class NoteViewModel(private val context: Context)  : ViewModel() {

    var repository : NoteRepository
    init {
        repository = Injection.providedNoteRepository(context)
    }

    var mAllNotes = repository.getNotes()
    private set


    suspend fun insert(note: Note) {
        repository.createNote(note)
    }

    suspend fun edit(note : Note){
        repository.editNote(note)
    }

    suspend fun remove(note : Note){
        repository.removeNote(note)
    }

}