package com.shohiebsense.myowntracking.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DIFF_CALLBACK) {


    private var listener : OnItemClickListener? = null
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description
                        && oldItem.priority == newItem.priority
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)

        holder.itemView.text_title.text = note.title
        holder.itemView.text_description.text = note.description
        holder.itemView.text_priority.text = note.priority.toString()
    }


    inner class NoteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
    }

    fun getNoteAt(position : Int) : Note {
        return getItem(position)
    }




interface OnItemClickListener {
    fun onItemClick(note : Note)
}

fun setOnItemClickListener(listener : OnItemClickListener){
    this.listener = listener
}

}