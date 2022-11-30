package com.rostelekom.simplenotes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rostelekom.simplenotes.R
import com.rostelekom.simplenotes.data.room.model.NotesModel

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var notes: List<NotesModel> = listOf()

    class NotesHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notes_title)
        val shortText: TextView = itemView.findViewById(R.id.notes_short_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent))
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.title.text = notes[position].noteTitle.getFirstSymbols(16)
        holder.shortText.text = notes[position].noteText.getFirstSymbols(100)
    }

    override fun getItemCount(): Int = notes.size

    private fun String.getFirstSymbols(count: Int): String = this.split("", limit=count).dropLast(1).joinToString("") + if (this.length > count) { "..." } else { "" }

    fun changeList(newNotesList: List<NotesModel>) {
        notes = newNotesList
        notifyDataSetChanged()
    }

}