package com.shohiebsense.myowntracking.ui.adapters.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.model.Cat
import kotlinx.android.synthetic.main.item_cat.view.*

class CatViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    var cat : Cat? = null

    companion object {
        fun create(parent: ViewGroup): CatViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cat, parent, false)
            return CatViewHolder(view)
        }
    }


    init {
        view.setOnClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
               // listener?.onItemClick(getItem(position))
            }
        }
    }


    fun updateCat(item: Cat?) {
        cat = item
        view.text_cat_name.text = "${item?.id ?: 0}"
    }
}