package com.shohiebsense.myowntracking.service.network.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.data.model.NetworkState
import com.shohiebsense.myowntracking.service.network.CatApi
import java.util.concurrent.Executor

class CatDataSource(
    private val catApi : CatApi,
    private val retryExecutor : Executor
    ) : PageKeyedDataSource<String, Cat>() {

    private var retry : (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    fun retryAllFailed() {
        val prevretry = retry
        retry = null
        prevretry?.let {
            retryExecutor.execute{
                it.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Cat>) {
        //execute cat api
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Cat>) {
        //
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Cat>) {
        //        // ignored, since we only ever append to our initial load
    }


}