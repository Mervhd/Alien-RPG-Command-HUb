package com.example.alienrpgcommandhub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.alienrpgcommandhub.R
import com.example.alienrpgcommandhub.data.AppDatabase
import com.example.alienrpgcommandhub.data.InventoryItem
import kotlinx.coroutines.launch

class InventoryTracker : Fragment() {

    private lateinit var itemNameEditText: EditText
    private lateinit var quantityEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory_tracker, container, false)

        itemNameEditText = view.findViewById(R.id.et_item_name)
        quantityEditText = view.findViewById(R.id.et_quantity)
        saveButton = view.findViewById(R.id.btn_save_inventory)

        saveButton.setOnClickListener {
            saveInventoryItem()
        }

        return view
    }

    private fun saveInventoryItem() {
        val itemName = itemNameEditText.text.toString()
        val quantity = quantityEditText.text.toString().toIntOrNull() ?: 0

        val inventoryItem = InventoryItem(
            itemName = itemName,
            quantity = quantity,
            // Add additional fields if needed
        )

        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(requireContext())
            database.inventoryDao().insert(inventoryItem)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InventoryTracker()
    }
}
