package com.shohiebsense.myowntracking.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.adapters.viewholder.CatViewHolder
import com.shohiebsense.myowntracking.adapters.viewholder.NetworkStateViewHolder
import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.data.model.NetworkState
import kotlinx.android.synthetic.main.item_cat.view.*

class CatsAdapter(
    private val retryCallback: () -> Unit
) : PagedListAdapter<Cat, RecyclerView.ViewHolder>(LIST_COMPARATOR) {

    private var listener : CatsAdapter.OnItemClickListener? = null
    var cat : Cat? = null

    private var networkState: NetworkState? = null
    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED


    companion object {
        val LIST_COMPARATOR = object: DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem.id == newItem.id

            override fun getChangePayload(oldItem: Cat, newItem: Cat): Any? {
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_cat -> CatViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent,retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        cat = getItem(position)
        holder.itemView.text_cat_name.text = cat?.id.toString()
        holder.itemView.image_cat.setImageURI(Uri.parse(cat?.url))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            (holder as CatViewHolder).updateCat(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_cat
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(cat: Cat?)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.listener = listener
    }
}