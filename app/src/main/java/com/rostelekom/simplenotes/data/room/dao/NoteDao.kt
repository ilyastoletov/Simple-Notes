package com.rostelekom.simplenotes.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rostelekom.simplenotes.data.room.model.NotesModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun noteInsert(model: NotesModel)

    @Delete
    suspend fun noteDelete(model: NotesModel)

    @Update
    suspend fun updateNote(model: NotesModel)

    @Query("SELECT * FROM notes_main")
    fun getAllNotes(): LiveData<List<NotesModel>>

}