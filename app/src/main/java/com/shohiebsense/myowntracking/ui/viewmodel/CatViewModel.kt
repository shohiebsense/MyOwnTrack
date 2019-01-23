package com.shohiebsense.myowntracking.ui.viewmodel

import androidx.lifecycle.*
import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import com.shohiebsense.myowntracking.data.CatRepository
import com.shohiebsense.myowntracking.model.Cat
import com.shohiebsense.myowntracking.model.CatSearchResult

class CatViewModel(
    private val repository: CatRepository
) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<CatSearchResult> = Transformations.map(queryLiveData, {
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