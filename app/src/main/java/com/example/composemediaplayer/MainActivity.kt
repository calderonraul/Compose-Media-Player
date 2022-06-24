package com.example.composemediaplayer

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemediaplayer.media.service.AlarmService
import com.example.composemediaplayer.presentation.viewmodel.SongsViewModel
import com.example.composemediaplayer.screens.HomeScreen
import com.example.composemediaplayer.ui.theme.ComposeMediaPlayerTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMediaPlayerTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                val permissionState = rememberPermissionState(
                    permission = Manifest.permission.READ_EXTERNAL_STORAGE
                )

                DisposableEffect(key1 = lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_RESUME) {
                            permissionState.launchPermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)


                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }

                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (permissionState.hasPermission) {
                        val audioViewModel = viewModel(
                            modelClass = SongsViewModel::class.java
                        )
                        val audioList = audioViewModel.audioList
                        HomeScreen(
                            progress = audioViewModel.currentAudioProgress.value,
                            onProgressChange = {
                                audioViewModel.seekTo(it)
                            },
                            isAudioPlaying = audioViewModel.isAudioPlaying,
                            audioList = audioList,
                            currentPlayingAudio = audioViewModel
                                .currentPlayingAudio.value,
                            onStart = {
                                audioViewModel.playAudio(it)
                            },
                            onItemClick = {
                                audioViewModel.playAudio(it)
                                setAlarm(1000)
                            },
                            onNext = {
                                audioViewModel.skipToNext()
                            }
                        )
                    } else {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "Grant permission first to use this app")
                        }
                    }
                }
            }
        }
    }


    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmService::class.java)


        val pendingFlags: Int = if (Util.SDK_INT >= 23) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, pendingFlags)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        //Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
}
