package com.shohiebsense.myowntracking.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.shohiebsense.myowntracking.model.Cat
import com.shohiebsense.myowntracking.api.cat.CatService
import com.shohiebsense.myowntracking.api.cat.getCats
import com.shohiebsense.myowntracking.db.CatLocalCache


class CatBoundaryCallback (val query: String,
                           val service : CatService,
                           val cache : CatLocalCache
)
: PagedList.BoundaryCallback<Cat>(){


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
    private var isRequestInProgress = false

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    // LiveData of network errors.

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors : LiveData<String>
        get() = _networkErrors



    /**
     * Database returned 0 items. We should query the backend for more items.
     */


    override fun onItemAtEndLoaded(itemAtEnd: Cat) {
        requestAndSaveData(query)
    }

    override fun onZeroItemsLoaded() {
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress){
            Log.e("shohiebsense ","request in progress")
            return
        }

        isRequestInProgress = true
        Log.e("shohiebsensee ","requesting "+lastRequestedPage)
        getCats(service, lastRequestedPage, {
                cats ->
            cache.insert(cats, {
                lastRequestedPage++
                isRequestInProgress = false
                cache.getSize()
            })
        },
            { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}