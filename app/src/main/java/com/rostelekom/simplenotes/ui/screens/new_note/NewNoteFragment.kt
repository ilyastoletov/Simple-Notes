package com.rostelekom.simplenotes.ui.screens.new_note

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rostelekom.simplenotes.R
import com.rostelekom.simplenotes.data.room.model.NotesModel
import com.rostelekom.simplenotes.databinding.FragmentNotesNewBinding

/* TODO 1. Сделать разбивку фрагмента по созданию/редактированию заметки
*  TODO 2. Сделать редактирование и удаление заметки */
class NewNoteFragment : Fragment() {

    private lateinit var binding: FragmentNotesNewBinding
    private val viewModel by lazy { ViewModelProvider(this).get(NewNoteViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesNewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        /* Setting up toolbar */
        requireActivity().actionBar?.hide()
        binding.notesEditingToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_notesNewFragment_to_notesListFragment)
        }

        binding.notesEditingToolbar.menu.findItem(R.id.menu_bar).isVisible = false

        /* Setting options menu */
        binding.notesEditingToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.apply_button -> {
                    insertNoteValidate(binding.etNoteTitle.text.toString(), binding.etNoteBody.text.toString())
                    true
                }
                else -> false
            }
        }

    }

    /* Validates note data and adding it to database */
    private fun insertNoteValidate(title: String, text: String) {
        if (title.isNotEmpty() && text.isNotEmpty()) {
            viewModel.insertNote(
                NotesModel(noteTitle = title, noteText = text)
            )
            Toast.makeText(context, "Заметка сохранена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_notesNewFragment_to_notesListFragment)
        } else {
            Toast.makeText(context, "Заголовок или текст заметки пусты", Toast.LENGTH_SHORT).show()
        }
    }

}