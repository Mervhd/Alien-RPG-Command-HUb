package com.example.alienrpgcommandhub.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val characterId: Int,
    val gear1: String? = null,
    val gear2: String? = null,
    val gear3: String? = null,
    val gear4: String? = null,
    val gear5: String? = null,
    val gear6: String? = null,
    val gear7: String? = null,
    val gear8: String? = null,
    val gear9: String? = null,
    val gear10: String? = null,
    val weapon1Name: String? = null,
    val weapon1Bonus: Int? = null,
    val weapon1Damage: Int? = null,
    val weapon1Range: String? = null,
    val weapon2Name: String? = null,
    val weapon2Bonus: Int? = null,
    val weapon2Damage: Int? = null,
    val weapon2Range: String? = null,
    val weapon3Name: String? = null,
    val weapon3Bonus: Int? = null,
    val weapon3Damage: Int? = null,
    val weapon3Range: String? = null,
    val weapon4Name: String? = null,
    val weapon4Bonus: Int? = null,
    val weapon4Damage: Int? = null,
    val weapon4Range: String? = null,
    val armorName: String? = null,
    val armorRating: Int? = null
)
