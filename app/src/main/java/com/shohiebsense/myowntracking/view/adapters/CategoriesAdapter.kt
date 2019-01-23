package com.shohiebsense.myowntracking.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.data.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter : ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(DIFF_CALLBACK) {


    private var listener : OnItemClickListener? = null
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name && oldItem.priority == newItem.priority
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        holder.itemView.text_name.text = category.name
    }


    inner class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
    }

    fun getCategoryAt(position : Int) : Category{
        return getItem(position)
    }




interface OnItemClickListener {
    fun onItemClick(category: Category)
}

fun setOnItemClickListener(listener : OnItemClickListener){
    this.listener = listener
}

}