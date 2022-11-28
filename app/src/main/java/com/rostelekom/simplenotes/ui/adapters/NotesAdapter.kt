package com.rostelekom.simplenotes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rostelekom.simplenotes.databinding.NotesItemBinding
import com.rostelekom.simplenotes.ui.models.Notes

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var notes: ArrayList<Notes> = arrayListOf()

    class NotesHolder(itemView: View, binding: NotesItemBinding): RecyclerView.ViewHolder(itemView) {
        val notesTitle = binding.notesTitle
        val notesShortText = binding.notesShortText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val binding: NotesItemBinding = NotesItemBinding.inflate(LayoutInflater.from(parent.context))
        return NotesHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.notesTitle.text = notes[position].title
        holder.notesShortText.text = notes[position].smallText
    }

    override fun getItemCount(): Int = notes.size

    fun changeList(newNotesList: ArrayList<Notes>) {
        notes = newNotesList
        notifyDataSetChanged()
    }

}