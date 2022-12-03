package com.rostelekom.simplenotes.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rostelekom.simplenotes.APP
import com.rostelekom.simplenotes.R
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

        holder.itemView.setOnClickListener {
            val navigateBundle = Bundle()
            navigateBundle.putInt("note_id", notes[position].id)
            navigateBundle.putString("note_title", notes[position].noteTitle)
            navigateBundle.putString("note_body", notes[position].noteText)
            APP.navController.navigate(R.id.action_notesListFragment_to_notesNewFragment, navigateBundle)
        }
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