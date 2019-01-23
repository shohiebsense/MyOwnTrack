package com.shohiebsense.myowntracking.db.repository

import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.dao.CategoryDao
import com.shohiebsense.myowntracking.model.Category
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class CategoryRepository(
    val categoryDao : CategoryDao
){


    suspend fun createCategory(category: Category){
        withContext(IO){
            categoryDao?.insert(category)
        }
    }

    suspend fun editCategory(category : Category){
        withContext(IO){
            categoryDao?.update(category)
        }
    }

    suspend fun removeCategory(category : Category){
        withContext(IO){
            categoryDao?.delete(category)
        }
    }

    fun getAll() = categoryDao?.getCategories()

}