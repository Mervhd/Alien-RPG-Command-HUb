package com.example.alienrpgcommandhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.alienrpgcommandhub.databinding.FragmentNotesAndLogsBinding

class NotesAndLogsFragment : Fragment() {

    private var _binding: FragmentNotesAndLogsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesAndLogsBinding.inflate(inflater, container, false)
        val view = binding.root

        val notesContainer: LinearLayout = binding.notesContainer
        val logsContainer: LinearLayout = binding.logsContainer
        val btnAddNote: Button = binding.btnAddNote
        val btnCreateLog: Button = binding.btnCreateLog

        btnAddNote.setOnClickListener {
            addNewEntry(notesContainer)
        }

        btnCreateLog.setOnClickListener {
            addNewEntry(logsContainer)
        }

        return view
    }

    private fun addNewEntry(container: LinearLayout) {
        val newEntry = EditText(requireContext())
        newEntry.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newEntry.hint = "Enter text here"
        container.addView(newEntry)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
