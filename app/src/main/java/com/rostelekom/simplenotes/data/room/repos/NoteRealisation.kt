package com.rostelekom.simplenotes.data.room.repos

import androidx.lifecycle.LiveData
import com.rostelekom.simplenotes.data.room.dao.NoteDao
import com.rostelekom.simplenotes.data.room.model.NotesModel

class NoteRealisation(private val dao: NoteDao) : NoteRepository {

    override val allNotes: LiveData<List<NotesModel>>
        get() = dao.getAllNotes()

    override suspend fun insertNote(model: NotesModel) {
        dao.noteInsert(model)
    }

    override suspend fun deleteNote(model: NotesModel) {
        dao.noteDelete(model)
    }

    override suspend fun editNote(model: NotesModel) {
        dao.updateNote(model)
    }
}