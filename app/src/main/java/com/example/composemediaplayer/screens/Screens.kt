package com.example.composemediaplayer.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composemediaplayer.data.model.Song
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun MusicItem(navController: NavController, item: Song) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(
                        4.dp
                    )
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = item.artworkUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                        .clickable { }
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)

                ) {
                    Text(text = "Title: " + item.title, style = MaterialTheme.typography.h6)
                    Text(
                        text = "Artist: " + item.artist,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(text = "Album: " + item.album)
                    TextButton(onClick = { navController.navigate("SecondScreen/") }) {
                        Text(text = "Show in full screen!")
                    }

                }
            }
        }

    }

}

@Composable
fun ExoPlayerUi(item: Song) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val mContext = LocalContext.current
        // Initializing ExoPLayer
        val mExoPlayer = remember(mContext) {
            ExoPlayer.Builder(mContext).build().apply {

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(item.url))
                    .build()
                setMediaItem(mediaItem)

                playWhenReady = true
                prepare()
            }
        }

        // Implementing ExoPlayer
        AndroidView(factory = { context ->
            StyledPlayerView(context).apply {
                player = mExoPlayer

            }
        })
    }

}
