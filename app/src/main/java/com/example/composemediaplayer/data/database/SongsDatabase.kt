package com.example.composemediaplayer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composemediaplayer.data.model.Song


@Database(entities = [Song::class], version = 1)
abstract class SongsDatabase : RoomDatabase() {
    abstract val songsDao: SongsDao
}