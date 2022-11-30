package com.rostelekom.simplenotes.data.room.repos

import androidx.lifecycle.LiveData
import com.rostelekom.simplenotes.data.room.dao.NoteDao
import com.rostelekom.simplenotes.data.room.model.NotesModel

class NoteRealisation(private val dao: NoteDao) : NoteRepository {

    override val allNotes: LiveData<List<NotesModel>>
        get() = dao.getAllNotes()

    override suspend fun insertNote(model: NotesModel, onSuccess: () -> Unit) {
        dao.noteInsert(model)
        onSuccess()
    }

    override suspend fun deleteNote(model: NotesModel, onSuccess: () -> Unit) {
        dao.noteDelete(model)
        onSuccess()
    }

    override suspend fun editNote(model: NotesModel, onSuccess: () -> Unit) {
        dao.updateNote(model)
        onSuccess()
    }
}