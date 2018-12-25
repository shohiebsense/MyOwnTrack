package com.shohiebsense.myowntracking.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shohiebsense.myowntracking.constants.DataConstants
import com.shohiebsense.myowntracking.data.model.Category

interface CategoryDao {

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category : Category)

    @Query("SELECT * FROM ${DataConstants.TABLE_CATEGORY}")
    fun getCategories()




}