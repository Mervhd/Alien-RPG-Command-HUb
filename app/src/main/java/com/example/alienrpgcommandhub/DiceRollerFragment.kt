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

    private var baseDiceCount = 0
    private var stressDiceCount = 0

    private lateinit var baseDiceCountView: TextView
    private lateinit var stressDiceCountView: TextView
    private lateinit var rolledDiceResultView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)

        baseDiceCountView = view.findViewById(R.id.base_dice_count)
        stressDiceCountView = view.findViewById(R.id.stress_dice_count)
        rolledDiceResultView = view.findViewById(R.id.rolled_dice_result)

        view.findViewById<Button>(R.id.button_add_base_dice).setOnClickListener {
            baseDiceCount++
            updateDiceCount()
        }

        view.findViewById<Button>(R.id.button_subtract_base_dice).setOnClickListener {
            if (baseDiceCount > 0) {
                baseDiceCount--
                updateDiceCount()
            }
        }

        view.findViewById<Button>(R.id.button_add_stress_dice).setOnClickListener {
            stressDiceCount++
            updateDiceCount()
        }

        view.findViewById<Button>(R.id.button_subtract_stress_dice).setOnClickListener {
            if (stressDiceCount > 0) {
                stressDiceCount--
                updateDiceCount()
            }
        }

        view.findViewById<Button>(R.id.button_roll_dice).setOnClickListener {
            rollDice()
        }

        view.findViewById<Button>(R.id.button_roll_d66).setOnClickListener {
            rollD66()
        }

        return view
    }

    private fun updateDiceCount() {
        baseDiceCountView.text = baseDiceCount.toString()
        stressDiceCountView.text = stressDiceCount.toString()
    }

    private fun rollDice() {
        val baseDiceResults = rollSpecificDice(baseDiceCount)
        val stressDiceResults = rollSpecificDice(stressDiceCount)
        val resultText = "Base Dice: ${baseDiceResults.joinToString(", ")}\nStress Dice: ${stressDiceResults.joinToString(", ")}"
        rolledDiceResultView.text = resultText
    }

    private fun rollSpecificDice(count: Int): List<Int> {
        return List(count) { Random.nextInt(1, 7) }
    }

    private fun rollD66() {
        val tens = rollD6()
        val units = rollD6()
        val result = tens * 10 + units
        rolledDiceResultView.text = "d66 Roll: $result"
    }

    private fun rollD6(): Int {
        return Random.nextInt(1, 7)
    }

}
