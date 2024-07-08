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
import com.example.alienrpgcommandhub.data.Character
import com.example.alienrpgcommandhub.data.Weapon
import kotlinx.coroutines.launch

class CharacterSheetFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var careerEditText: EditText
    private lateinit var appearanceEditText: EditText
    private lateinit var talentsEditText: EditText
    private lateinit var personalAgendaEditText: EditText
    private lateinit var rivalEditText: EditText
    private lateinit var buddyEditText: EditText
    private lateinit var stressLevelEditText: EditText
    private lateinit var healthEditText: EditText
    private lateinit var radiationEditText: EditText
    private lateinit var airEditText: EditText
    private lateinit var foodEditText: EditText
    private lateinit var powerEditText: EditText
    private lateinit var waterEditText: EditText
    private lateinit var strengthEditText: EditText
    private lateinit var closeCombatEditText: EditText
    private lateinit var heavyMachineryEditText: EditText
    private lateinit var staminaEditText: EditText
    private lateinit var agilityEditText: EditText
    private lateinit var rangedCombatEditText: EditText
    private lateinit var mobilityEditText: EditText
    private lateinit var pilotingEditText: EditText
    private lateinit var empathyEditText: EditText
    private lateinit var commandEditText: EditText
    private lateinit var manipulationEditText: EditText
    private lateinit var medicalEditText: EditText
    private lateinit var witsEditText: EditText
    private lateinit var observationEditText: EditText
    private lateinit var survivalEditText: EditText
    private lateinit var comtechEditText: EditText
    private lateinit var experiencePointsEditText: EditText
    private lateinit var storyPointsEditText: EditText
    private lateinit var gearEditTexts: List<EditText>
    private lateinit var weaponNameEditText: EditText
    private lateinit var weaponBonusEditText: EditText
    private lateinit var weaponDamageEditText: EditText
    private lateinit var weaponRangeEditText: EditText
    private lateinit var armorNameEditText: EditText
    private lateinit var armorRatingEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_sheet, container, false)
        initializeViews(view)
        saveButton.setOnClickListener {
            saveCharacter()
        }
        return view
    }

    private fun initializeViews(view: View) {
        nameEditText = view.findViewById(R.id.et_name)
        careerEditText = view.findViewById(R.id.et_career)
        appearanceEditText = view.findViewById(R.id.et_appearance)
        talentsEditText = view.findViewById(R.id.et_talents)
        personalAgendaEditText = view.findViewById(R.id.et_personal_agenda)
        rivalEditText = view.findViewById(R.id.et_rival)
        buddyEditText = view.findViewById(R.id.et_buddy)
        stressLevelEditText = view.findViewById(R.id.et_stress_level)
        healthEditText = view.findViewById(R.id.et_health)
        radiationEditText = view.findViewById(R.id.et_radiation)
        airEditText = view.findViewById(R.id.et_air)
        foodEditText = view.findViewById(R.id.et_food)
        powerEditText = view.findViewById(R.id.et_power)
        waterEditText = view.findViewById(R.id.et_water)
        strengthEditText = view.findViewById(R.id.et_strength)
        closeCombatEditText = view.findViewById(R.id.et_close_combat)
        heavyMachineryEditText = view.findViewById(R.id.et_heavy_machinery)
        staminaEditText = view.findViewById(R.id.et_stamina)
        agilityEditText = view.findViewById(R.id.et_agility)
        rangedCombatEditText = view.findViewById(R.id.et_ranged_combat)
        mobilityEditText = view.findViewById(R.id.et_mobility)
        pilotingEditText = view.findViewById(R.id.et_piloting)
        empathyEditText = view.findViewById(R.id.et_empathy)
        commandEditText = view.findViewById(R.id.et_command)
        manipulationEditText = view.findViewById(R.id.et_manipulation)
        medicalEditText = view.findViewById(R.id.et_medical)
        witsEditText = view.findViewById(R.id.et_wits)
        observationEditText = view.findViewById(R.id.et_observation)
        survivalEditText = view.findViewById(R.id.et_survival)
        comtechEditText = view.findViewById(R.id.et_comtech)
        experiencePointsEditText = view.findViewById(R.id.et_experience_points)
        storyPointsEditText = view.findViewById(R.id.et_story_points)
        gearEditTexts = listOf(
            view.findViewById(R.id.et_gear1),
            view.findViewById(R.id.et_gear2),
            view.findViewById(R.id.et_gear3),
            view.findViewById(R.id.et_gear4),
            view.findViewById(R.id.et_gear5),
            view.findViewById(R.id.et_gear6),
            view.findViewById(R.id.et_gear7),
            view.findViewById(R.id.et_gear8),
            view.findViewById(R.id.et_gear9),
            view.findViewById(R.id.et_gear10)
        )
        weaponNameEditText = view.findViewById(R.id.et_weapon1_name)
        weaponBonusEditText = view.findViewById(R.id.et_weapon1_bonus)
        weaponDamageEditText = view.findViewById(R.id.et_weapon1_damage)
        weaponRangeEditText = view.findViewById(R.id.et_weapon1_range)
        armorNameEditText = view.findViewById(R.id.et_armor_name)
        armorRatingEditText = view.findViewById(R.id.et_armor_rating)
        saveButton = view.findViewById(R.id.btn_save_character)
    }

    private fun saveCharacter() {
        val character = Character(
            name = nameEditText.text.toString(),
            career = careerEditText.text.toString(),
            appearance = appearanceEditText.text.toString(),
            talents = talentsEditText.text.toString(),
            personalAgenda = personalAgendaEditText.text.toString(),
            rival = rivalEditText.text.toString(),
            buddy = buddyEditText.text.toString(),
            stressLevel = stressLevelEditText.text.toString().toIntOrNull() ?: 0,
            health = healthEditText.text.toString().toIntOrNull() ?: 0,
            radiation = radiationEditText.text.toString().toIntOrNull() ?: 0,
            air = airEditText.text.toString().toIntOrNull() ?: 0,
            food = foodEditText.text.toString().toIntOrNull() ?: 0,
            power = powerEditText.text.toString().toIntOrNull() ?: 0,
            water = waterEditText.text.toString().toIntOrNull() ?: 0,
            strength = strengthEditText.text.toString().toIntOrNull() ?: 0,
            closeCombat = closeCombatEditText.text.toString().toIntOrNull() ?: 0,
            heavyMachinery = heavyMachineryEditText.text.toString().toIntOrNull() ?: 0,
            stamina = staminaEditText.text.toString().toIntOrNull() ?: 0,
            agility = agilityEditText.text.toString().toIntOrNull() ?: 0,
            rangedCombat = rangedCombatEditText.text.toString().toIntOrNull() ?: 0,
            mobility = mobilityEditText.text.toString().toIntOrNull() ?: 0,
            piloting = pilotingEditText.text.toString().toIntOrNull() ?: 0,
            empathy = empathyEditText.text.toString().toIntOrNull() ?: 0,
            command = commandEditText.text.toString().toIntOrNull() ?: 0,
            manipulation = manipulationEditText.text.toString().toIntOrNull() ?: 0,
            medical = medicalEditText.text.toString().toIntOrNull() ?: 0,
            wits = witsEditText.text.toString().toIntOrNull() ?: 0,
            observation = observationEditText.text.toString().toIntOrNull() ?: 0,
            survival = survivalEditText.text.toString().toIntOrNull() ?: 0,
            comtech = comtechEditText.text.toString().toIntOrNull() ?: 0,
            experiencePoints = experiencePointsEditText.text.toString().toIntOrNull() ?: 0,
            storyPoints = storyPointsEditText.text.toString().toIntOrNull() ?: 0,
            gear = gearEditTexts.map { it.text.toString() },
            weapons = listOf(
                Weapon(
                    name = weaponNameEditText.text.toString(),
                    bonus = weaponBonusEditText.text.toString().toIntOrNull() ?: 0,
                    damage = weaponDamageEditText.text.toString().toIntOrNull() ?: 0,
                    range = weaponRangeEditText.text.toString()
                )
            ),
            armorName = armorNameEditText.text.toString(),
            armorRating = armorRatingEditText.text.toString().toIntOrNull() ?: 0
        )

        lifecycleScope.launch {
            val database = AppDatabase.getDatabase(requireContext())
            database.characterDao().insert(character)
        }
    }
}
