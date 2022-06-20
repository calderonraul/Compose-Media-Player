package com.example.composemediaplayer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composemediaplayer.presentation.services.MusicService
import com.example.composemediaplayer.presentation.viewmodel.SongsViewModel
import com.example.composemediaplayer.screens.MusicScreen
import com.example.composemediaplayer.ui.theme.ComposeMediaPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

import com.example.composemediaplayer.presentation.services.foregroundStartService


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMediaPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StartApp()
                }
            }
        }
    }
}

@Composable
fun MusicInit(viewModel: SongsViewModel = hiltViewModel(), navController: NavController) {
    MusicScreen(state = viewModel.registerState, navController = navController)
}

@Composable
fun StartApp() {
    val navController = rememberNavController()
    MusicInit(navController = navController)
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeMediaPlayerTheme {
        Greeting("Android")
    }
}