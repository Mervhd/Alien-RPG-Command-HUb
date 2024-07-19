package com.example.alienrpgcommandhub.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alienrpgcommandhub.R
import kotlin.random.Random

class DiceRollerFragment : Fragment() {

    // Variables to keep track of the number of base and stress dice
    private var baseDiceCount = 0
    private var stressDiceCount = 0

    // UI elements to display the dice counts and rolled results
    private lateinit var baseDiceCountView: TextView
    private lateinit var stressDiceCountView: TextView
    private lateinit var rolledDiceResultView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)

        // Initialize the TextViews for displaying dice counts and results
        baseDiceCountView = view.findViewById(R.id.base_dice_count)
        stressDiceCountView = view.findViewById(R.id.stress_dice_count)
        rolledDiceResultView = view.findViewById(R.id.rolled_dice_result)

        // Set click listener for the add base dice button
        view.findViewById<Button>(R.id.button_add_base_dice).setOnClickListener {
            baseDiceCount++ // Increment base dice count
            updateDiceCount() // Update the displayed count
        }

        // Set click listener for the subtract base dice button
        view.findViewById<Button>(R.id.button_subtract_base_dice).setOnClickListener {
            if (baseDiceCount > 0) {
                baseDiceCount-- // Decrement base dice count
                updateDiceCount() // Update the displayed count
            }
        }

        // Set click listener for the add stress dice button
        view.findViewById<Button>(R.id.button_add_stress_dice).setOnClickListener {
            stressDiceCount++ // Increment stress dice count
            updateDiceCount() // Update the displayed count
        }

        // Set click listener for the subtract stress dice button
        view.findViewById<Button>(R.id.button_subtract_stress_dice).setOnClickListener {
            if (stressDiceCount > 0) {
                stressDiceCount-- // Decrement stress dice count
                updateDiceCount() // Update the displayed count
            }
        }

        // Set click listener for the roll dice button
        view.findViewById<Button>(R.id.button_roll_dice).setOnClickListener {
            rollDice() // Roll the dice and update the result
        }

        // Set click listener for the roll d66 button
        view.findViewById<Button>(R.id.button_roll_d66).setOnClickListener {
            rollD66() // Roll a d66 and update the result
        }

        return view // Return the inflated view
    }

    // Function to update the displayed dice counts
    private fun updateDiceCount() {
        baseDiceCountView.text = baseDiceCount.toString()
        stressDiceCountView.text = stressDiceCount.toString()
    }

    // Function to roll the base and stress dice and display the results
    private fun rollDice() {
        val baseDiceResults = rollSpecificDice(baseDiceCount) // Roll base dice
        val stressDiceResults = rollSpecificDice(stressDiceCount) // Roll stress dice
        // Create a result text showing both base and stress dice results
        val resultText = "Base Dice: ${baseDiceResults.joinToString(", ")}\nStress Dice: ${stressDiceResults.joinToString(", ")}"
        rolledDiceResultView.text = resultText // Update the result view
    }

    // Function to roll a specified number of dice and return the results
    private fun rollSpecificDice(count: Int): List<Int> {
        return List(count) { Random.nextInt(1, 7) } // Roll each die and return the list of results
    }

    // Function to roll a d66 (two d6 dice) and display the result
    private fun rollD66() {
        val tens = rollD6() // Roll the tens place die
        val units = rollD6() // Roll the units place die
        val result = tens * 10 + units // Calculate the d66 result
        rolledDiceResultView.text = "d66 Roll: $result" // Update the result view
    }

    // Function to roll a single d6 die
    private fun rollD6(): Int {
        return Random.nextInt(1, 7) // Return a random number between 1 and 6
    }

}
