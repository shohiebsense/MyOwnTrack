package com.shohiebsense.myowntracking.ui.viewmodel

import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.AndroidViewModel
import com.shohiebsense.myowntracking.db.repository.NoteRepository
import com.shohiebsense.myowntracking.model.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository : NoteRepository = NoteRepository.getInstance(application)
    var mAllNotes = mRepository.getNotes()
    private set


    suspend fun insert(note: Note) {
        mRepository.createNote(note)
    }

    suspend fun edit(note : Note){
        mRepository.editNote(note)
    }

    suspend fun remove(note : Note){
        mRepository.removeNote(note)
    }


}