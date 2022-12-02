package com.rostelekom.simplenotes.data.room.repos

import androidx.lifecycle.LiveData
import com.rostelekom.simplenotes.data.room.model.NotesModel

interface NoteRepository {

    val allNotes: LiveData<List<NotesModel>>

    suspend fun insertNote(model: NotesModel)
    suspend fun deleteNote(model: NotesModel)
    suspend fun editNote(model: NotesModel)

}