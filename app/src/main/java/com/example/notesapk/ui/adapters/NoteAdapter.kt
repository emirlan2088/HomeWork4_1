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
    private val onLongClick: NoteFragment,
    private val onClick: NoteFragment
    ): ListAdapter<NoteModel, NoteAdapter.NoteViewHolder>(DiffCalback()) {
    class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtText.text = item.description
            binding.root.setBackgroundColor(item.color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(note)
            true
        }
        holder.itemView.setOnClickListener{
            onClick.onClick(note)
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


