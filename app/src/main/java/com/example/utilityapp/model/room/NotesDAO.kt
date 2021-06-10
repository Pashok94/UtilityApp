package com.example.utilityapp.model.room

import androidx.room.*

@Dao
interface NotesDAO {
    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: NoteEntity)

    @Delete
    fun delete(entity: NoteEntity)
}