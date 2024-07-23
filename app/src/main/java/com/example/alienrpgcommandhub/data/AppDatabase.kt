package com.example.alienrpgcommandhub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(entities = [Character::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

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
                // Create a new table with the correct schema
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

                // Copy the data from the old table to the new table
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

                // Remove the old table
                db.execSQL("DROP TABLE characters")

                // Rename the new table to the old table name
                db.execSQL("ALTER TABLE characters_new RENAME TO characters")
            }
        }
    }
}
