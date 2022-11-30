package com.rostelekom.simplenotes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rostelekom.simplenotes.data.room.dao.NoteDao
import com.rostelekom.simplenotes.data.room.model.NotesModel

@Database(entities = [NotesModel::class], version = 2)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        private var database: NotesDatabase ?= null

        @Synchronized
        fun getDatabaseInstance(context: Context): NotesDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, NotesDatabase::class.java, "notes_db").build()
                database as NotesDatabase
            } else {
                database as NotesDatabase
            }
        }
    }
}