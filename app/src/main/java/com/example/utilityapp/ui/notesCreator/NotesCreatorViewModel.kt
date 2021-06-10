package com.example.utilityapp.ui.notesCreator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utilityapp.AppState
import com.example.utilityapp.MainActivity
import com.example.utilityapp.model.Note
import com.example.utilityapp.model.Repository
import com.example.utilityapp.model.RepositoryImpl

class NotesCreatorViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = RepositoryImpl(MainActivity.getNotesDao())
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun addNewNote(note: Note){
        db.addNewNote(note)
    }
}