package com.shohiebsense.myowntracking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shohiebsense.myowntracking.utils.constants.DataConstants
import com.shohiebsense.myowntracking.data.model.Note

@Dao
interface NoteDao{

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note : Note)

    @Delete
    fun delete(note : Note)

    //@Transaction
    @Query("DELETE FROM ${DataConstants.TABLE_NOTE}")
    fun deleteAllNotes()

    //@Transaction
    @Query("SELECT * from ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.priority}")
    fun getAllNotesByPriority() : LiveData<List<Note>>

    //@Transaction
    @Query("SELECT * FROM ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.modifiedTime} DESC")
    fun getAllNotesByTimeRecently() : LiveData<List<Note>>

    //@Transaction
    @Query("SELECT * FROM ${DataConstants.TABLE_NOTE} ORDER BY ${DataConstants.ATTRIBUTE.createdTime}")
    fun getAllNotesByTimeAdded() : LiveData<List<Note>>


}