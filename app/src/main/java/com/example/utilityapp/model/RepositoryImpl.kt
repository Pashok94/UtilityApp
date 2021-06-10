package com.example.utilityapp.model

import com.example.utilityapp.model.room.NoteEntity
import com.example.utilityapp.model.room.NotesDAO

class RepositoryImpl(private val localeDataSource: NotesDAO) : Repository {
    override fun getUserNotes(): ArrayList<Note> {
        return convertNoteEntityToNotesList(localeDataSource.getAll())
    }

    override fun addNewNote(note: Note) {
        localeDataSource.insert(convertNoteToNoteEntity(note))
    }

    override fun deleteNote(note: Note) {
        localeDataSource.delete(convertNoteToNoteEntity(note))
    }


    private fun convertNoteEntityToNotesList(entityList: List<NoteEntity>): ArrayList<Note> {
        return ArrayList(
            entityList.map {
                Note(
                    it.title,
                    it.description,
                    it.date
                )
            }
        )
    }

    private fun convertNoteToNoteEntity(note: Note): NoteEntity {
        return with(note){
            NoteEntity(date, title, description)
        }
    }
}