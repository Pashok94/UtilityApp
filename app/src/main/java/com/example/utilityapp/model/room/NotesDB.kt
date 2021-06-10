package com.example.utilityapp.model.room

import androidx.room.*

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun noteDao(): NotesDAO
}