package com.example.alienrpgcommandhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.alienrpgcommandhub.data.AppDatabase
import com.example.alienrpgcommandhub.data.Note
import com.example.alienrpgcommandhub.data.Log
import com.example.alienrpgcommandhub.databinding.FragmentNotesAndLogsBinding
import kotlinx.coroutines.launch

class NotesAndLogsFragment : Fragment() {

    // Use nullable binding for safe handling of views
    private var _binding: FragmentNotesAndLogsBinding? = null
    // Provide safe access to the binding with a backing property
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesAndLogsBinding.inflate(inflater, container, false)
        setupViewListeners()
        return binding.root // Directly return the root of the binding
    }

    private fun setupViewListeners() {
        // Consolidated function to set up all button listeners
        binding.btnAddNote.setOnClickListener {
            addNewEntry(binding.notesContainer)
        }

        binding.btnCreateLog.setOnClickListener {
            addNewEntry(binding.logsContainer)
        }

        binding.btnSaveNotes.setOnClickListener {
            saveNotes()
        }

        binding.btnSaveLogs.setOnClickListener {
            saveLogs()
        }
    }

    private fun addNewEntry(container: LinearLayout) {
        val newEntry = EditText(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = "Enter text here"
        }
        container.addView(newEntry)
    }

    private fun saveNotes() {
        val notesList = mutableListOf<Note>()
        for (i in 0 until binding.notesContainer.childCount) {
            (binding.notesContainer.getChildAt(i) as? EditText)?.let { editText ->
                notesList.add(Note(characterId = 1, content = editText.text.toString()))
            }
        }
        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(requireContext())
            database.notesAndLogsDao().insertAllNotes(notesList)
        }
    }

    private fun saveLogs() {
        val logsList = mutableListOf<Log>()
        for (i in 0 until binding.logsContainer.childCount) {
            (binding.logsContainer.getChildAt(i) as? EditText)?.let { editText ->
                logsList.add(Log(characterId = 1, log = editText.text.toString()))
            }
        }
        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(requireContext())
            database.notesAndLogsDao().insertAllLogs(logsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Properly dispose of the binding when the view is destroyed
    }
}
