package com.sgu.kampusgo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

//id starts at 0. autoGenerate tells Room to replace that 0 with
//the next id when the row is inserted. The table name is notes.
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val body: String,
)