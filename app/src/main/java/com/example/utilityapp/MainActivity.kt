package com.example.utilityapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.utilityapp.model.room.NotesDAO
import com.example.utilityapp.model.room.NotesDB
import com.example.utilityapp.ui.notes.NotesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        appContext = this.applicationContext
        setSupportActionBar(findViewById(R.id.materialToolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NotesFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        private lateinit var appContext: Context
        private var db: NotesDB? = null
        private const val DB_NAME = "Notes.db"

        fun getNotesDao(): NotesDAO {
            if (db == null) {
                synchronized(NotesDB::class.java) {
                    db = Room.databaseBuilder(
                        appContext,
                        NotesDB::class.java,
                        DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return db!!.noteDao()
        }
    }
}