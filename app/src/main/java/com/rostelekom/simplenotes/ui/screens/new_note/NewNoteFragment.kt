package com.rostelekom.simplenotes.ui.screens.new_note

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rostelekom.simplenotes.R
import com.rostelekom.simplenotes.data.room.model.NotesModel
import com.rostelekom.simplenotes.databinding.FragmentNotesNewBinding

class NewNoteFragment : Fragment() {

    private lateinit var binding: FragmentNotesNewBinding
    private val viewModel by lazy { ViewModelProvider(this).get(NewNoteViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesNewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().actionBar?.hide()

        /* Setting up toolbar */
        binding.notesEditingToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_notesNewFragment_to_notesListFragment)
        }

        /* Checking if note new or editing */
        val noteFlag = requireArguments().getBoolean("new_note")
        if (noteFlag) {
            initNewNote()
        } else {
            initEditingNote(requireArguments())
        }

    }

    private fun initNewNote() {

        binding.notesEditingToolbar.menu.findItem(R.id.menu_bar).isVisible = false

        /* Setting options menu */
        binding.notesEditingToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.apply_button -> {
                    saveNewOrEditedNote(binding.etNoteTitle.text.toString(), binding.etNoteBody.text.toString(), new = true)
                    true
                }
                else -> false
            }
        }

    }

    /* Validates note data and adding it to database */
    private fun saveNewOrEditedNote(title: String, text: String, noteId: Int=0, new: Boolean) {
        if (title.isNotEmpty() && text.isNotEmpty()) {
            if (new) {
                viewModel.insertNote(
                    NotesModel(noteTitle = title, noteText = text)
                )
            } else {
                viewModel.editNote(
                    NotesModel(id = noteId, noteTitle = title, noteText = text)
                )
            }
            Toast.makeText(context, "Заметка сохранена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_notesNewFragment_to_notesListFragment)
        } else {
            Toast.makeText(context, "Заголовок или текст заметки пусты", Toast.LENGTH_SHORT).show()
        }
    }

    /// Внимание! Ниже талмуд говнокода
    private fun initEditingNote(bundle: Bundle) {

        /* visibility of buttons */
        binding.notesEditingToolbar.title = "Редактировать"
        binding.notesEditingToolbar.menu.findItem(R.id.edit_note).isVisible = true
        binding.notesEditingToolbar.menu.findItem(R.id.menu_bar).isVisible = true
        binding.notesEditingToolbar.menu.findItem(R.id.apply_button).isVisible = false

        /* setting edit text */
        binding.editingNoteTvTitle.visibility = View.VISIBLE
        binding.editingNoteTvBody.visibility = View.VISIBLE
        binding.etNoteTitle.visibility = View.GONE
        binding.etNoteBody.visibility = View.GONE

        binding.editingNoteTvTitle.text = bundle.getString("note_title")
        binding.editingNoteTvBody.text = bundle.getString("note_body")

        /* setting menu click listener */
        binding.notesEditingToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_note -> {
                    binding.etNoteTitle.visibility = View.VISIBLE
                    binding.etNoteBody.visibility = View.VISIBLE

                    binding.etNoteTitle.setText(bundle.getString("note_title"), TextView.BufferType.EDITABLE)
                    binding.etNoteBody.setText(bundle.getString("note_body"), TextView.BufferType.EDITABLE)

                    binding.editingNoteTvTitle.visibility = View.GONE
                    binding.editingNoteTvBody.visibility = View.GONE

                    binding.notesEditingToolbar.menu.findItem(R.id.apply_editing_button).isVisible = true
                    binding.notesEditingToolbar.menu.findItem(R.id.edit_note).isVisible = false

                    true
                }
                R.id.apply_editing_button -> {
                    saveNewOrEditedNote(
                        binding.etNoteTitle.text.toString(),
                        binding.etNoteBody.text.toString(),
                        bundle.getInt("note_id"),
                        new = false
                    )
                    true
                }
                R.id.menu_bar -> {
                    confirmDeletingNote(
                        NotesModel(
                            id = bundle.getInt("note_id"),
                            binding.etNoteTitle.text.toString(),
                            binding.etNoteBody.text.toString()
                        )
                    )
                    true
                }
                else -> false
            }
        }

    }

    private fun confirmDeletingNote(model: NotesModel) {

        val listener = DialogInterface.OnClickListener { dialog, element ->
            when (element) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.deleteNote(model)
                    findNavController().navigate(R.id.action_notesNewFragment_to_notesListFragment)
                    Toast.makeText(context, "Заметка удалена", Toast.LENGTH_SHORT).show()
                }
                DialogInterface.BUTTON_NEGATIVE -> dialog.cancel()
            }
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("Удалить заметку")
            .setCancelable(true)
            .setMessage("Действительно хотите удалить заметку?")
            .setPositiveButton("Да", listener)
            .setNegativeButton("Отмена", listener)
            .setOnCancelListener {
                it.cancel()
            }
            .create()

        dialog.show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ffffff"))
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ffffff"))

    }

}