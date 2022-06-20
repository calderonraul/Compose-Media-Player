package com.example.composemediaplayer.domain

import com.example.composemediaplayer.data.model.Song
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    suspend fun getSongs(): Flow<List<Song>>
    suspend fun playSong(id: Int): Flow<Song>
    suspend fun initSongs()
}