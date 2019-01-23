package com.shohiebsense.myowntracking.service.db

import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.service.network.CatApi
import java.util.concurrent.Executor


class CatBoundaryCallback (private val handleResponse: (String, CatApi.ListingResponse?) -> Unit,
private val ioExecutor: Executor,
private val networkPageSize: Int)
: PagedList.BoundaryCallback<Cat>(){

    val helper = Paging(ioExecutor)
    val networkState = helper.createStatusLiveData()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getTop(
                subreddit = subredditName,
                limit = networkPageSize)
                .enqueue(createWebserviceCallback(it))
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getTopAfter(
                subreddit = subredditName,
                after = itemAtEnd.name,
                limit = networkPageSize)
                .enqueue(createWebserviceCallback(it))
        }
    }

    /**
     * every time it gets new items, boundary callback simply inserts them into the database and
     * paging library takes care of refreshing the list if necessary.
     */
    private fun insertItemsIntoDb(
        response: Response<RedditApi.ListingResponse>,
        it: PagingRequestHelper.Request.Callback) {
        ioExecutor.execute {
            handleResponse(subredditName, response.body())
            it.recordSuccess()
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: RedditPost) {
        // ignored, since we only ever append to what's in the DB
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<RedditApi.ListingResponse> {
        return object : Callback<RedditApi.ListingResponse> {
            override fun onFailure(
                call: Call<RedditApi.ListingResponse>,
                t: Throwable) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<RedditApi.ListingResponse>,
                response: Response<RedditApi.ListingResponse>) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}