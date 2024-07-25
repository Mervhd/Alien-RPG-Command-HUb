package com.example.alienrpgcommandhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesAndLogsDao {

    // Note operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNotes(notes: List<Note>)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    // Log operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLogs(logs: List<Log>)

    @Update
    suspend fun updateLog(log: Log)

    @Delete
    suspend fun deleteLog(log: Log)

    @Query("SELECT * FROM logs")
    fun getAllLogs(): LiveData<List<Log>>
}
