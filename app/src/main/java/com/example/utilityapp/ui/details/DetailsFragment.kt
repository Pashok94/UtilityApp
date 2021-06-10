package com.example.utilityapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.utilityapp.databinding.DetailsFragmentBinding
import com.example.utilityapp.model.Note
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {
    companion object {
        const val BUNDLE_EXTRA = "new_note"
        fun newInstance(bundle: Bundle): Fragment {
            val fragmentDetails = DetailsFragment()
            fragmentDetails.arguments = bundle
            return fragmentDetails
        }
    }

    private var _binding: DetailsFragmentBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var note: Note
    private val liveData by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        note = arguments?.getParcelable(BUNDLE_EXTRA)!!
        with(binding) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvDate.text = "Заметка создана:\n${dateFormat(note.date)}"
        }
        binding.deleteBtn.setOnClickListener {
            liveData.deleteNote(note)
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun dateFormat(millis: Long) : String {
        val date = Date(millis)
        val dateFormat = SimpleDateFormat("dd/MM/yy")
        return dateFormat.format(date)
    }
}