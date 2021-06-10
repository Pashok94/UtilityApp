package com.example.utilityapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utilityapp.AppState
import com.example.utilityapp.MainActivity
import com.example.utilityapp.model.Note
import com.example.utilityapp.model.Repository
import com.example.utilityapp.model.RepositoryImpl

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = RepositoryImpl(MainActivity.getNotesDao())
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun deleteNote(note: Note){
        db.deleteNote(note)
    }
}