package com.shohiebsense.myowntracking.service.repository.cat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.service.Listing
import java.util.concurrent.Executor

class CatDbRepository(private val db : AppDatabase,
                      private val networkExecutor: Executor): CatRepository{



    override fun getCats(): Listing<Cat> {
        val boundaryCallback = CatB(
            webservice = redditApi,
            subredditName = subReddit,
            handleResponse = this::insertResultIntoDb,
            ioExecutor = ioExecutor,
            networkPageSize = networkPageSize)
        // we are using a mutable live data to trigger refresh requests which eventually calls
        // refresh method and gets a new live data. Each refresh request by the user becomes a newly
        // dispatched data in refreshTrigger
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh(subReddit)
        }

        // We use toLiveData Kotlin extension function here, you could also use LivePagedListBuilder
        val livePagedList = db.cats().cats().toLiveData(
            pageSize = pageSize,
            boundaryCallback = boundaryCallback)

        return Listing(
            pagedList = livePagedList,
            networkState = boundaryCallback.networkState,
            retry = {
                boundaryCallback.helper.retryAllFailed()
            },
            refresh = {
                refreshTrigger.value = null
            },
            refreshState = refreshState
        )
    }


}