package com.example.composemediaplayer.presentation.viewmodel

import com.example.composemediaplayer.data.model.Song
import kotlinx.coroutines.flow.StateFlow

data class SongUiState(
    val songsFLow:StateFlow<List<Song>>,
    val songFlow:StateFlow<Song>,
    val numberValue:StateFlow<Int>,
    val onNumberValueChanged: (Int) -> Unit,
    val fetchSongs: () -> Unit,
    val fetchOneSong: () -> Unit,
)