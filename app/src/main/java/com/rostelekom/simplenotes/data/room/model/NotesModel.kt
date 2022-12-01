package com.rostelekom.simplenotes.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_main")
data class NotesModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var noteTitle: String = "",

    @ColumnInfo
    var noteText: String = ""

)
