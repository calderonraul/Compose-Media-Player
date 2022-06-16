package com.example.composemediaplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemediaplayer.data.model.Song
import com.example.composemediaplayer.domain.SongsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel@Inject constructor(private val songsRepository: SongsRepository) : ViewModel() {

    private val songsData: MutableStateFlow<List<Song>> = MutableStateFlow(emptyList())
    private val numberValueFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val songData: MutableStateFlow<Song> =
        MutableStateFlow(
            Song(
                album = "",
                artist = "",
                title = "",
                duration = "",
                url = "",
                artworkUrl = "",
                id = 0
            )
        )


    private fun getSongs() {
        viewModelScope.launch {
            songsRepository.getSongs().collect {
                songsData.value = it
            }
        }
    }

    private fun getSongById() {
        viewModelScope.launch {
            songsRepository.playSong(numberValueFlow.value).collect {
                songData.value = it
            }
        }
    }

    private fun onNumberChanged(number: Int) {
        numberValueFlow.value = number
    }


    val registerState = SongUiState(
        songsFLow = songsData,
        numberValue = numberValueFlow,
        onNumberValueChanged = ::onNumberChanged,
        fetchOneSong = ::getSongById,
        fetchSongs = ::getSongs
    )


}


