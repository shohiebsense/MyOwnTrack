package com.shohiebsense.myowntracking.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import com.shohiebsense.myowntracking.data.CatRepository
import com.shohiebsense.myowntracking.model.Cat
import com.shohiebsense.myowntracking.model.CatSearchResult
import com.shohiebsense.myowntracking.utils.Injection

class CatViewModel(
   context : Context
) : ViewModel() {

    var repository : CatRepository
    init {
        repository = Injection.provideGithubRepository(context)
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<CatSearchResult> = Transformations.map(queryLiveData, {
        Log.e("shohiebsee ","live data "+it)
        repository.search(it)
    })

    val repos: LiveData<PagedList<Cat>> = Transformations.switchMap(repoResult,
        { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult,
        { it -> it.networkErrors })

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}