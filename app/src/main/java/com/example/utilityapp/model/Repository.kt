package com.example.utilityapp.model

interface Repository {
    fun getUserNotes(): ArrayList<Note>
    fun addNewNote(note: Note)
    fun deleteNote(note: Note)
}