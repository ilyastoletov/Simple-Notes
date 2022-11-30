package com.rostelekom.simplenotes.data.room.repos

import androidx.lifecycle.LiveData
import com.rostelekom.simplenotes.data.room.model.NotesModel

interface NoteRepository {

    val allNotes: LiveData<List<NotesModel>>

    suspend fun insertNote(model: NotesModel, onSuccess: () -> Unit)
    suspend fun deleteNote(model: NotesModel, onSuccess: () -> Unit)
    suspend fun editNote(model: NotesModel, onSuccess: () -> Unit)

}