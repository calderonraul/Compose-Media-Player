package com.example.composemediaplayer.data

import com.example.composemediaplayer.data.database.SongsDao
import com.example.composemediaplayer.data.model.Song
import com.example.composemediaplayer.domain.SongsRepository
import kotlinx.coroutines.flow.Flow

class SongsRepositoryImpl(private val dao: SongsDao) : SongsRepository {

    override suspend fun getSongs(): Flow<List<Song>> {
        return dao.getAllSongsFromRoom()
    }

    override suspend fun playSong(id: Int): Flow<Song> {
        return dao.getSongByIdFromRoom(id)
    }

    override suspend fun initSongs() {

        //create a Song object and insert it into the database
        val song1 = Song(
            id = 1,
            title = "Song 1",
            artist = "Artist 1",
            album = "Album 1",
            duration = "00:00:00",
            url ="https://storage.googleapis.com/exoplayer-test-media-0/play.mp3",
            artworkUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )
        val song2 = Song(
            id = 2,
            title = "Song 2",
            artist = "Artist 2",
            album = "Album 2",
            duration = "00:00:00",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3",
            artworkUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )
        val song3 = Song(
            id = 3,
            title = "Song 3",
            artist = "Artist 3",
            album = "Album 3",
            duration = "00:00:00",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3",
            artworkUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )
        val song4 = Song(
            id = 4,
            title = "Song 4",
            artist = "Artist 4",
            album = "Album 4",
            duration = "00:00:00",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3",
            artworkUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )
        val song5=Song(
            id = 4,
            title = "Song 4",
            artist = "Artist 4",
            album = "Album 4",
            duration = "00:00:00",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3",
            artworkUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )

        val listOfSongs = listOf(song1, song2, song3,song4,song5)

        //go trough the list and insert each song into the database
        for (song in listOfSongs) {
            dao.insertSongToRoom(song)
        }


    }

}