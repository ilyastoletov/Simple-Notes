package com.rostelekom.simplenotes.ui.screens.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rostelekom.simplenotes.REPOSITORY
import com.rostelekom.simplenotes.data.room.NotesDatabase
import com.rostelekom.simplenotes.data.room.model.NotesModel
import com.rostelekom.simplenotes.data.room.repos.NoteRealisation
import com.rostelekom.simplenotes.ui.models.Notes

class NotesListViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase() {
        val daoNote = NotesDatabase.getDatabaseInstance(context).getNoteDao()
        REPOSITORY = NoteRealisation(daoNote)
    }

    fun getAllNotes(): LiveData<List<NotesModel>> = REPOSITORY.allNotes
}