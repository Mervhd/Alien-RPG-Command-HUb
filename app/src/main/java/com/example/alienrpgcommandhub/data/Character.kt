package com.example.alienrpgcommandhub.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val career: String,
    val appearance: String,
    val talents: String,
    val personalAgenda: String,
    val rival: String,
    val buddy: String,
    val stressLevel: Int,
    val health: Int,
    val radiation: Int,
    val air: Int,
    val food: Int,
    val power: Int,
    val water: Int,
    val strength: Int,
    val closeCombat: Int,
    val heavyMachinery: Int,
    val stamina: Int,
    val agility: Int,
    val rangedCombat: Int,
    val mobility: Int,
    val piloting: Int,
    val empathy: Int,
    val command: Int,
    val manipulation: Int,
    val medical: Int,
    val wits: Int,
    val observation: Int,
    val survival: Int,
    val comtech: Int,
    val experiencePoints: Int,
    val storyPoints: Int,
    val gear: List<String> = emptyList(),
    val weapons: List<Weapon> = emptyList(),
   // val armorName: String,
    //val armorRating: Int
)

data class Weapon(
    val name: String,
    val bonus: Int,
    val damage: Int,
    val range: String
)
