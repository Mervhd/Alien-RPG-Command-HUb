package com.example.alienrpgcommandhub.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class Log(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val characterId: Int,
    val log: String
)
