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

    private lateinit var etGear1: EditText
    private lateinit var etGear2: EditText
    private lateinit var etGear3: EditText
    private lateinit var etGear4: EditText
    private lateinit var etGear5: EditText
    private lateinit var etGear6: EditText
    private lateinit var etGear7: EditText
    private lateinit var etGear8: EditText
    private lateinit var etGear9: EditText
    private lateinit var etGear10: EditText
    private lateinit var etWeapon1Name: EditText
    private lateinit var etWeapon1Bonus: EditText
    private lateinit var etWeapon1Damage: EditText
    private lateinit var etWeapon1Range: EditText
    private lateinit var etWeapon2Name: EditText
    private lateinit var etWeapon2Bonus: EditText
    private lateinit var etWeapon2Damage: EditText
    private lateinit var etWeapon2Range: EditText
    private lateinit var etWeapon3Name: EditText
    private lateinit var etWeapon3Bonus: EditText
    private lateinit var etWeapon3Damage: EditText
    private lateinit var etWeapon3Range: EditText
    private lateinit var etWeapon4Name: EditText
    private lateinit var etWeapon4Bonus: EditText
    private lateinit var etWeapon4Damage: EditText
    private lateinit var etWeapon4Range: EditText
    private lateinit var etArmorName: EditText
    private lateinit var etArmorRating: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventory_tracker, container, false)

        etGear1 = view.findViewById(R.id.et_gear1)
        etGear2 = view.findViewById(R.id.et_gear2)
        etGear3 = view.findViewById(R.id.et_gear3)
        etGear4 = view.findViewById(R.id.et_gear4)
        etGear5 = view.findViewById(R.id.et_gear5)
        etGear6 = view.findViewById(R.id.et_gear6)
        etGear7 = view.findViewById(R.id.et_gear7)
        etGear8 = view.findViewById(R.id.et_gear8)
        etGear9 = view.findViewById(R.id.et_gear9)
        etGear10 = view.findViewById(R.id.et_gear10)
        etWeapon1Name = view.findViewById(R.id.et_weapon1_name)
        etWeapon1Bonus = view.findViewById(R.id.et_weapon1_bonus)
        etWeapon1Damage = view.findViewById(R.id.et_weapon1_damage)
        etWeapon1Range = view.findViewById(R.id.et_weapon1_range)
        etWeapon2Name = view.findViewById(R.id.et_weapon2_name)
        etWeapon2Bonus = view.findViewById(R.id.et_weapon2_bonus)
        etWeapon2Damage = view.findViewById(R.id.et_weapon2_damage)
        etWeapon2Range = view.findViewById(R.id.et_weapon2_range)
        etWeapon3Name = view.findViewById(R.id.et_weapon3_name)
        etWeapon3Bonus = view.findViewById(R.id.et_weapon3_bonus)
        etWeapon3Damage = view.findViewById(R.id.et_weapon3_damage)
        etWeapon3Range = view.findViewById(R.id.et_weapon3_range)
        etWeapon4Name = view.findViewById(R.id.et_weapon4_name)
        etWeapon4Bonus = view.findViewById(R.id.et_weapon4_bonus)
        etWeapon4Damage = view.findViewById(R.id.et_weapon4_damage)
        etWeapon4Range = view.findViewById(R.id.et_weapon4_range)
        etArmorName = view.findViewById(R.id.et_armor_name)
        etArmorRating = view.findViewById(R.id.et_armor_rating)
        saveButton = view.findViewById(R.id.btn_save_inventory)

        saveButton.setOnClickListener {
            saveInventoryItem()
        }

        return view
    }

    private fun saveInventoryItem() {
        val inventoryItem = InventoryItem(
            characterId = 1, // Replace with actual character ID
            gear1 = etGear1.text.toString(),
            gear2 = etGear2.text.toString(),
            gear3 = etGear3.text.toString(),
            gear4 = etGear4.text.toString(),
            gear5 = etGear5.text.toString(),
            gear6 = etGear6.text.toString(),
            gear7 = etGear7.text.toString(),
            gear8 = etGear8.text.toString(),
            gear9 = etGear9.text.toString(),
            gear10 = etGear10.text.toString(),
            weapon1Name = etWeapon1Name.text.toString(),
            weapon1Bonus = etWeapon1Bonus.text.toString().toIntOrNull(),
            weapon1Damage = etWeapon1Damage.text.toString().toIntOrNull(),
            weapon1Range = etWeapon1Range.text.toString(),
            weapon2Name = etWeapon2Name.text.toString(),
            weapon2Bonus = etWeapon2Bonus.text.toString().toIntOrNull(),
            weapon2Damage = etWeapon2Damage.text.toString().toIntOrNull(),
            weapon2Range = etWeapon2Range.text.toString(),
            weapon3Name = etWeapon3Name.text.toString(),
            weapon3Bonus = etWeapon3Bonus.text.toString().toIntOrNull(),
            weapon3Damage = etWeapon3Damage.text.toString().toIntOrNull(),
            weapon3Range = etWeapon3Range.text.toString(),
            weapon4Name = etWeapon4Name.text.toString(),
            weapon4Bonus = etWeapon4Bonus.text.toString().toIntOrNull(),
            weapon4Damage = etWeapon4Damage.text.toString().toIntOrNull(),
            weapon4Range = etWeapon4Range.text.toString(),
            armorName = etArmorName.text.toString(),
            armorRating = etArmorRating.text.toString().toIntOrNull()
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
