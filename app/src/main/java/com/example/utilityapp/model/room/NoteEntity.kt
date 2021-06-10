package com.example.utilityapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey
    val date: Long,
    val title: String,
    val description: String
)