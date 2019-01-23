package com.shohiebsense.myowntracking.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shohiebsense.myowntracking.model.Cat

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cats : List<Cat>)

    @Query("SELECT * FROM cats")
    fun getCats() : DataSource.Factory<Int, Cat>

    @Query("SELECT COUNT(*) FROM cats")
    fun getSize() : Int
}