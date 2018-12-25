package com.shohiebsense.myowntracking.data.repository

import com.shohiebsense.myowntracking.data.dao.CategoryDao
import com.shohiebsense.myowntracking.data.model.Category
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class CategoryRepository private constructor(
    private val categoryDao: CategoryDao
){
    companion object {
        @Volatile private var instance : CategoryRepository? = null

        fun getInstance(categoryDao: CategoryDao) =
            instance ?: synchronized(this){
                instance ?: CategoryRepository(categoryDao)
                    .also { instance = it }
            }
    }

    suspend fun createCategory(category: Category){
        withContext(IO){
            categoryDao.insert(category)
        }
    }

    suspend fun removeCategory(category : Category){
        withContext(IO){
            categoryDao.delete(category)
        }
    }

    fun getAll() = categoryDao.getCategories()

}