package com.shohiebsense.myowntracking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shohiebsense.myowntracking.constants.DataConstants
import com.shohiebsense.myowntracking.data.model.Category

@Dao
interface CategoryDao {

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category : Category)

    //@Transaction
    @Query("SELECT * FROM ${DataConstants.TABLE_CATEGORY}")
    fun getCategories() : LiveData<List<Category>>




}