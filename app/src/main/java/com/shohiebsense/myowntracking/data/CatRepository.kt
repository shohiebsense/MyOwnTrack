package com.shohiebsense.myowntracking.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.shohiebsense.myowntracking.api.cat.CatService
import com.shohiebsense.myowntracking.db.CatLocalCache
import com.shohiebsense.myowntracking.model.CatSearchResult

class CatRepository(
    val service : CatService,
    val cache : CatLocalCache) {

    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): CatSearchResult {
        Log.d("GithubRepository", "New query: $query")
        //lastRequestedPage = 1
        //requestAndSaveData(query)

        // Get data from the local cache
        val dataSourceFactory = cache.getCats()

        //get the pagedlist
        val repoBoundaryCallback = CatBoundaryCallback(query, service, cache)
        val networkErrors = repoBoundaryCallback.networkErrors

        val data =
            LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(repoBoundaryCallback)
                .build()

        return CatSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

}