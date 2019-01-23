package com.shohiebsense.myowntracking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shohiebsense.myowntracking.data.model.Cat

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cats : List<Cat>)

    @Query("SELECT * FROM cats")
    fun cats() : List<Cat>

}