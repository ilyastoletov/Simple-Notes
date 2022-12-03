package com.rostelekom.simplenotes.ui.screens.new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rostelekom.simplenotes.REPOSITORY
import com.rostelekom.simplenotes.data.room.model.NotesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewNoteViewModel : ViewModel() {

    fun insertNote(model: NotesModel) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.insertNote(model)
        }

    fun editNote(model: NotesModel) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.editNote(model)
        }

    fun deleteNote(model: NotesModel) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.deleteNote(model)
        }

}