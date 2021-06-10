package com.example.utilityapp.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utilityapp.R
import com.example.utilityapp.databinding.NotesFragmentRecyclerItemBinding
import com.example.utilityapp.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesFragmentAdapter(private val callback: (Note) -> Unit) : RecyclerView.Adapter<NotesFragmentAdapter.NotesViewHolder>() {
    var notesList: List<Note> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notes_fragment_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.onBind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = NotesFragmentRecyclerItemBinding.bind(itemView)
        fun onBind(note: Note) {
            with(binding) {
                noteTitle.text = note.title
                noteDescription.text = note.description
                noteDate.text = dateFormat(note.date)
            }
            itemView.setOnClickListener {
                callback.invoke(note)
            }
        }

        private fun dateFormat(millis: Long) : String {
            val date = Date(millis)
            val dateFormat = SimpleDateFormat("dd/MM/yy")
            return dateFormat.format(date)
        }
    }
}