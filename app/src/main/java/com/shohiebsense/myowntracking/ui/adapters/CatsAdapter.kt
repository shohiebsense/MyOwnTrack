package com.shohiebsense.myowntracking.ui.adapters

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.ui.adapters.viewholder.CatViewHolder
import com.shohiebsense.myowntracking.ui.adapters.viewholder.NetworkStateViewHolder
import com.shohiebsense.myowntracking.model.Cat
import com.shohiebsense.myowntracking.model.NetworkState
import kotlinx.android.synthetic.main.item_cat.view.*

class CatsAdapter : PagedListAdapter<Cat, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as CatViewHolder).updateCat(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem == newItem
        }
    }
}