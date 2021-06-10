package com.example.utilityapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.utilityapp.AppState
import com.example.utilityapp.R
import com.example.utilityapp.databinding.NotesFragmentBinding
import com.example.utilityapp.model.Note
import com.example.utilityapp.ui.details.DetailsFragment
import com.example.utilityapp.ui.notesCreator.NotesCreatorFragment
import com.google.android.material.button.MaterialButton

class NotesFragment : Fragment() {
    companion object {
        fun newInstance() = NotesFragment()
    }

    private var _binding: NotesFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val liveData by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        NotesFragmentAdapter(callback)
    }

    private lateinit var addNoteBtn: MaterialButton

    private val callback = fun(note: Note){
        startDetailsFragment(note)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = binding.notesRw
        recyclerView.adapter = adapter
        addNoteBtn = binding.addNoteButton

        val observer = Observer<AppState> {
            renderData(it)
        }
        liveData.getLiveData().observe(viewLifecycleOwner, observer)

        addNoteBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, NotesCreatorFragment.newInstance())?.addToBackStack("")?.commit()
        }
    }

    private fun startDetailsFragment(note: Note){
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, note)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, DetailsFragment.newInstance(bundle))?.addToBackStack("")?.commit()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> adapter.notesList = appState.results
        }
    }
}