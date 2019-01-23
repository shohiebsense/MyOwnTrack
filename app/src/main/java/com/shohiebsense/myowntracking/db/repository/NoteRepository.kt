package com.shohiebsense.myowntracking.db.repository

import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.dao.NoteDao
import com.shohiebsense.myowntracking.model.Note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NoteRepository (val noteDao : NoteDao) {


    suspend fun createNote(note: Note) {
        withContext(IO) {
            noteDao?.insert(note)
        }
    }

    suspend fun editNote(note: Note) {
        withContext(IO) {
            noteDao?.update(note)
        }
    }

    suspend fun removeNote(note: Note) {
        withContext(IO) {
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

