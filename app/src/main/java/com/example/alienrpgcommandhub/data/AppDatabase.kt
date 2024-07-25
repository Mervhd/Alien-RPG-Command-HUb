package com.example.alienrpgcommandhub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(entities = [Character::class, Note::class, Log::class, InventoryItem::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun notesAndLogsDao(): NotesAndLogsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "alien_rpg_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Define the migration strategy from version 1 to version 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Migration for characters table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `characters_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `name` TEXT NOT NULL, 
                        `career` TEXT NOT NULL, 
                        `appearance` TEXT NOT NULL, 
                        `talents` TEXT NOT NULL, 
                        `personalAgenda` TEXT NOT NULL, 
                        `rival` TEXT NOT NULL, 
                        `buddy` TEXT NOT NULL, 
                        `stressLevel` INTEGER NOT NULL, 
                        `health` INTEGER NOT NULL, 
                        `radiation` INTEGER NOT NULL, 
                        `air` INTEGER NOT NULL, 
                        `food` INTEGER NOT NULL, 
                        `power` INTEGER NOT NULL, 
                        `water` INTEGER NOT NULL, 
                        `strength` INTEGER NOT NULL, 
                        `closeCombat` INTEGER NOT NULL, 
                        `heavyMachinery` INTEGER NOT NULL, 
                        `stamina` INTEGER NOT NULL, 
                        `agility` INTEGER NOT NULL, 
                        `rangedCombat` INTEGER NOT NULL, 
                        `mobility` INTEGER NOT NULL, 
                        `piloting` INTEGER NOT NULL, 
                        `empathy` INTEGER NOT NULL, 
                        `command` INTEGER NOT NULL, 
                        `manipulation` INTEGER NOT NULL, 
                        `medical` INTEGER NOT NULL, 
                        `wits` INTEGER NOT NULL, 
                        `observation` INTEGER NOT NULL, 
                        `survival` INTEGER NOT NULL, 
                        `comtech` INTEGER NOT NULL, 
                        `experiencePoints` INTEGER NOT NULL, 
                        `storyPoints` INTEGER NOT NULL, 
                        `gear` TEXT NOT NULL, 
                        `weapons` TEXT NOT NULL
                    )
                """)

                db.execSQL("""
                    INSERT INTO characters_new (
                        id, name, career, appearance, talents, personalAgenda, 
                        rival, buddy, stressLevel, health, radiation, air, food, 
                        power, water, strength, closeCombat, heavyMachinery, stamina, 
                        agility, rangedCombat, mobility, piloting, empathy, command, 
                        manipulation, medical, wits, observation, survival, comtech, 
                        experiencePoints, storyPoints, gear, weapons
                    ) 
                    SELECT 
                        id, name, career, appearance, talents, personalAgenda, 
                        rival, buddy, stressLevel, health, radiation, air, food, 
                        power, water, strength, closeCombat, heavyMachinery, stamina, 
                        agility, rangedCombat, mobility, piloting, empathy, command, 
                        manipulation, medical, wits, observation, survival, comtech, 
                        experiencePoints, storyPoints, gear, weapons 
                    FROM characters
                """)

                db.execSQL("DROP TABLE characters")
                db.execSQL("ALTER TABLE characters_new RENAME TO characters")

                // Migration for notes table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `notes_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `characterId` INTEGER NOT NULL, 
                        `content` TEXT NOT NULL,
                        FOREIGN KEY(`characterId`) REFERENCES `characters`(`id`) ON DELETE CASCADE
                    )
                """)

                db.execSQL("""
                    INSERT INTO notes_new (
                        id, characterId, content
                    )
                    SELECT 
                        id, characterId, content
                    FROM notes
                """)

                db.execSQL("DROP TABLE notes")
                db.execSQL("ALTER TABLE notes_new RENAME TO notes")

                // Migration for logs table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `logs_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `characterId` INTEGER NOT NULL, 
                        `log` TEXT NOT NULL,
                        FOREIGN KEY(`characterId`) REFERENCES `characters`(`id`) ON DELETE CASCADE
                    )
                """)

                db.execSQL("""
                    INSERT INTO logs_new (
                        id, characterId, log
                    )
                    SELECT 
                        id, characterId, log
                    FROM logs
                """)

                db.execSQL("DROP TABLE logs")
                db.execSQL("ALTER TABLE logs_new RENAME TO logs")

                // Migration for inventory table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `inventory_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `characterId` INTEGER NOT NULL, 
                        `itemName` TEXT NOT NULL,
                        `quantity` INTEGER NOT NULL,
                        `armorName` TEXT,  
                        `armorRating` INTEGER,  
                        FOREIGN KEY(`characterId`) REFERENCES `characters`(`id`) ON DELETE CASCADE
                    )
                """)

                db.execSQL("""
                    INSERT INTO inventory_new (
                        id, characterId, itemName, quantity, armorName, armorRating
                    )
                    SELECT 
                        id, characterId, itemName, quantity, armorName, armorRating
                    FROM inventory
                """)

                db.execSQL("DROP TABLE inventory")
                db.execSQL("ALTER TABLE inventory_new RENAME TO inventory")
            }
        }
    }
}
