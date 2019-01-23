package com.shohiebsense.myowntracking.ui.viewmodel

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.CatRepository
import com.shohiebsense.myowntracking.db.repository.CategoryRepository
import com.shohiebsense.myowntracking.model.Category
import com.shohiebsense.myowntracking.utils.Injection
import kotlin.coroutines.coroutineContext

class CategoryViewModel(private val context: Context) : ViewModel() {

    var repository : CategoryRepository
    init {
        repository = Injection.provideCategoryRepository(context)
    }


    var mAllCategories = repository.getAll()
    private set

    suspend fun insert(category: Category) {
        repository.createCategory(category)
    }

    suspend fun edit(category: Category){
        repository.editCategory(category)
    }

    suspend fun remove(category : Category){
        repository.removeCategory(category)
    }

}