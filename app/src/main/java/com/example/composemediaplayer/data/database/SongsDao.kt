package com.example.composemediaplayer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composemediaplayer.data.model.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs_table")
    suspend fun getAllSongsFromRoom(): Flow<List<Song>>

    @Query("SELECT * FROM songs_table WHERE id = :id")
    suspend fun getSongByIdFromRoom(id: Int): Flow<Song>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongToRoom(song: Song)

    @Query("DELETE FROM songs_table")
    suspend fun deleteAllSongsFromRoom()


}