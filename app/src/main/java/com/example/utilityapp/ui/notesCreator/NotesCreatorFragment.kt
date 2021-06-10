package com.example.utilityapp.ui.notesCreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.utilityapp.databinding.NotesCreatorFragmentBinding
import com.example.utilityapp.model.Note
import com.google.android.material.button.MaterialButton

class NotesCreatorFragment : Fragment() {
    private var _binding: NotesCreatorFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val liveData by lazy {
        ViewModelProvider(this).get(NotesCreatorViewModel::class.java)
    }

    private lateinit var titleET: EditText
    private lateinit var descriptionET: EditText
    private lateinit var addBtn: MaterialButton

    companion object {
        fun newInstance() = NotesCreatorFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotesCreatorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding) {
            titleET = etNoteTitle
            descriptionET = etNoteDescription
            addBtn = button
        }

        addBtn.setOnClickListener {
            if (titleET.text.toString() != "" && descriptionET.text.toString() != "") {
                liveData.addNewNote(
                    Note(
                        titleET.text.toString(),
                        descriptionET.text.toString(),
                        System.currentTimeMillis()
                    )
                )
                activity?.supportFragmentManager?.popBackStack()
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }
}