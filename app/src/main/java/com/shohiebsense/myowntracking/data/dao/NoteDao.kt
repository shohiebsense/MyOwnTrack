package com.shohiebsense.myowntracking.data.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.shohiebsense.myowntracking.constants.DataConstants
import com.shohiebsense.myowntracking.data.model.Note

@Dao
interface NoteDao{

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note : Note)

    @Delete
    fun delete(note : Note)

    @Query("DELETE FROM ${DataConstants.TABLE_NOTE}")
    fun deleteAllNotes()


    @Query("SELECT * from ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.priority}")
    fun getAllNotesByPriority() : MutableLiveData<List<Note>>

    @Query("SELECT * FROM ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.modifiedTime} DESC")
    fun getAllNotesByTimeRecently() : MutableLiveData<List<Note>>

    @Query("SELECT * FROM ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.createdTime}")
    fun getAllNotesByTimeAdded() : MutableLiveData<List<Note>>



}