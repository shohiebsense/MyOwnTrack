package com.shohiebsense.myowntracking.viewmodel

import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.AndroidViewModel
import com.shohiebsense.myowntracking.data.model.Note
import com.shohiebsense.myowntracking.data.repository.NoteRepository

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