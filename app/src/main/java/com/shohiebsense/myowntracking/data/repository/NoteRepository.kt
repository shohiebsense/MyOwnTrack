package com.shohiebsense.myowntracking.data.repository

import com.shohiebsense.myowntracking.data.dao.NoteDao
import com.shohiebsense.myowntracking.data.model.Note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NoteRepository private constructor(
    private val noteDao : NoteDao
) {

    companion object {
        @Volatile private var instance : NoteRepository? = null

        fun getInstance(noteDao: NoteDao) =
            instance ?: synchronized(this) {
                instance ?: NoteRepository(noteDao)
                    .also { instance = it }
            }
    }

    suspend fun createNote(note : Note){
        withContext(IO){
            noteDao.insert(note)
        }
    }

    suspend fun removeNote(note : Note){
        withContext(IO){
            noteDao.delete(note)
        }
    }

    fun getNotesByCreatedTime() =
        noteDao.getAllNotesByTimeAdded()

    fun getNotesRecently() =
        noteDao.getAllNotesByTimeRecently()

    fun getNotes() =
        noteDao.getAllNotesByPriority()










}