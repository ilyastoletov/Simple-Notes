package com.rostelekom.simplenotes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rostelekom.simplenotes.data.room.model.NotesModel
import com.rostelekom.simplenotes.databinding.NotesItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var notes: List<NotesModel> = listOf()

    class NotesHolder(itemView: View, binding: NotesItemBinding): RecyclerView.ViewHolder(itemView) {
        val title: TextView = binding.notesTitle
        val shortText: TextView = binding.notesShortText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context))
        return NotesHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.title.text = notes[position].noteTitle.getFirstSymbols(14)
        holder.shortText.text = notes[position].noteText.getFirstSymbols(140)
    }

    override fun getItemCount(): Int = notes.size

    private fun String.getFirstSymbols(count: Int): String =
        this.split("", limit=count)
            .dropLast(1)
            .joinToString("") + if (this.length > count) { "..." } else { "" }

    fun changeList(newNotesList: List<NotesModel>) {
        notes = newNotesList
        notifyDataSetChanged()
    }

}