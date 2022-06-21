package com.example.composemediaplayer.data.repository

import com.example.composemediaplayer.data.ContentResolverHelper
import com.example.composemediaplayer.data.model.Audio


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioRepository @Inject
constructor(private val contentResolverHelper: ContentResolverHelper) {
    suspend fun getAudioData():List<Audio> = withContext(Dispatchers.IO){
        contentResolverHelper.getAudioData()
    }
}