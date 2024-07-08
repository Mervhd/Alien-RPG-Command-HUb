package com.example.alienrpgcommandhub.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromWeaponList(value: String): List<Weapon> {
        val listType = object : TypeToken<List<Weapon>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromWeaponList(list: List<Weapon>): String {
        return Gson().toJson(list)
    }
}
