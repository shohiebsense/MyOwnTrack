package com.shohiebsense.myowntracking.view.viewmodel

import androidx.lifecycle.AndroidViewModel
import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.model.Category
import com.shohiebsense.myowntracking.service.repository.CategoryRepository

class CategoryViewModel(application : Application) : AndroidViewModel(application) {

    private var mRepository : CategoryRepository = CategoryRepository.getInstance(application)
    var mAllCategories = mRepository.getAll()
    private set

    suspend fun insert(category: Category) {
        mRepository.createCategory(category)
    }

    suspend fun edit(category: Category){
        mRepository.editCategory(category)
    }

    suspend fun remove(category : Category){
        mRepository.removeCategory(category)
    }

}