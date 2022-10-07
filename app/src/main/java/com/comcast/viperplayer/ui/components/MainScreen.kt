package com.comcast.viperplayer.ui.components

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.comcast.viperplayer.ui.PlayerViewModel
import com.comcast.viperplayer.ui.theme.ViperPlayerTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(playerViewModel: PlayerViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("COMCAST | Viper | Player", color = Color.White) },
                backgroundColor = Color(0xff0f9d58)
            )
        },
        content = {
            ViperPlayer(playerViewModel.videoToPlay, playerViewModel.analyticsCollector)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViperPlayerTheme {
        MainScreen(viewModel())
    }
}