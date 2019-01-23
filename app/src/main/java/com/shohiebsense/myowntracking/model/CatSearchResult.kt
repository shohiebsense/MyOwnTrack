package com.shohiebsense.myowntracking.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class CatSearchResult(
    val data: LiveData<PagedList<Cat>>,
    val networkErrors: LiveData<String>
) {
}