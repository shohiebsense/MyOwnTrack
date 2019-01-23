/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shohiebsense.myowntracking.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.shohiebsense.myowntracking.api.cat.CatService
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.CatRepository
import com.shohiebsense.myowntracking.db.CatLocalCache
import com.shohiebsense.myowntracking.db.repository.CategoryRepository
import com.shohiebsense.myowntracking.db.repository.NoteRepository
import com.shohiebsense.myowntracking.ui.viewmodel.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): CatLocalCache {
        val database = AppDatabase.getInstance(context)
        return CatLocalCache(database!!.catDao(), Executors.newSingleThreadExecutor())
    }


    private fun provideDatabase(context : Context) : AppDatabase {
        return AppDatabase.getInstance(context)!!
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    fun provideGithubRepository(context: Context): CatRepository {
        return CatRepository(CatService(), provideCache(context))
    }

    fun providedNoteRepository(context: Context): NoteRepository {
        return NoteRepository(provideDatabase(context).noteDao())
    }

    fun provideCategoryRepository(context : Context) : CategoryRepository {
        return CategoryRepository(provideDatabase(context).categoryDao())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(context)
    }

}