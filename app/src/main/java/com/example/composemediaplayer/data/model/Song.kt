package com.example.composemediaplayer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs_table")
data class Song(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val artist: String,
    val album: String,
    val duration: String,
    val url: String,
    val artworkUrl: String
)


