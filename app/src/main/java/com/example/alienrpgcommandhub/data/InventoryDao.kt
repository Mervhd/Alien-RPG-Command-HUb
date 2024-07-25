package com.example.alienrpgcommandhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InventoryDao {
    @Insert
    suspend fun insert(inventoryItem: InventoryItem)

    @Insert
    suspend fun insertAll(inventoryItems: List<InventoryItem>)

    @Query("SELECT * FROM inventory")
    suspend fun getAllItems(): List<InventoryItem>
}
