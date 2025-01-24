package com.example.notesapk.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapk.data.models.NoteModel
import com.example.notesapk.databinding.ItemNoteBinding
import com.example.notesapk.ui.fragment.note.NoteFragment

class NoteAdapter(
    private val onLongClick: NoteFragment
    ): ListAdapter<NoteModel, NoteAdapter.NoteViewHolder>(DiffCalback()) {
    class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtText.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
    }
    class DiffCalback() : DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
           return oldItem == newItem
        }

    }
}


