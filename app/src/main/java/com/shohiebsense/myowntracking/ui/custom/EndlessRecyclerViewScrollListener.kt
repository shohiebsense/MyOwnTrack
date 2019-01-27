package com.shohiebsense.myowntracking.ui.custom

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


//this listener is when you use an offset. Offset is triggered by the size of current list
abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 3

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    abstract val loading: Boolean

    constructor(layoutManager: LinearLayoutManager) {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager: GridLayoutManager) {
        this.mLayoutManager = layoutManager
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount()
    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        this.mLayoutManager = layoutManager
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount()
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            onListScrolled()
        }
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager!!.getItemCount()

        if (mLayoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions =
                (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        } else if (mLayoutManager is LinearLayoutManager) {
            lastVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        } else if (mLayoutManager is GridLayoutManager) {
            lastVisibleItemPosition = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            onLoadMore()
        }
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore()

    fun onListScrolled() {}


}
