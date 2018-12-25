package com.shohiebsense.myowntracking.data.repository

import android.app.Application
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.dao.NoteDao
import com.shohiebsense.myowntracking.data.model.Note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NoteRepository private constructor(
    private val application: Application
) {

    private val noteDao = AppDatabase.getInstance(application.applicationContext)?.noteDao()

    companion object {
        @Volatile private var instance : NoteRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this) {
                instance ?: NoteRepository(application)
                    .also { instance = it }
            }
    }

    suspend fun createNote(note : Note){
        withContext(IO){
            noteDao?.insert(note)
        }
    }

    suspend fun editNote(note : Note){
        withContext(IO){
            noteDao?.update(note)
        }
    }

    suspend fun removeNote(note : Note){
        withContext(IO){
            noteDao?.delete(note)
        }
    }

    fun getNotesByCreatedTime() =
        noteDao?.getAllNotesByTimeAdded()

    fun getNotesRecently() =
        noteDao?.getAllNotesByTimeRecently()

    fun getNotes() =
        noteDao?.getAllNotesByPriority()










}