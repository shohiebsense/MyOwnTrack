package com.shohiebsense.myowntracking.service.repository

import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.model.Category
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class CategoryRepository private constructor(
    private val application: Application
){

    private val categoryDao = AppDatabase.getInstance(application.applicationContext)?.categoryDao()

    companion object {
        @Volatile private var instance : CategoryRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this){
                instance ?: CategoryRepository(application)
                    .also { instance = it }
            }
    }

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