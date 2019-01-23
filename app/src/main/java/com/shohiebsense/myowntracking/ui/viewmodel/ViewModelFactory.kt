package com.shohiebsense.myowntracking.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shohiebsense.myowntracking.data.CatRepository
import com.shohiebsense.myowntracking.db.repository.NoteRepository

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatViewModel(context) as T
        }
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(context) as T
        }
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}