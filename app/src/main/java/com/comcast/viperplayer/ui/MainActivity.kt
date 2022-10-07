package com.comcast.viperplayer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.comcast.viperplayer.PlayerManager
import com.comcast.viperplayer.data.VIDEO_URL
import com.comcast.viperplayer.ui.components.MainScreen
import com.comcast.viperplayer.ui.theme.ViperPlayerTheme

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class MainActivity : ComponentActivity() {

    private val playerViewModel by viewModels<PlayerViewModel>() {
        PlayerViewModelFactory(PlayerManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ViperPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(playerViewModel)
                }
            }
        }
        playerViewModel.playVideo(VIDEO_URL)
    }
}
