package com.comcast.viperplayer.ui.components

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.comcast.viperplayer.data.VIDEO_URL
import com.comcast.viperplayer.ui.theme.ViperPlayerTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("COMCAST | Viper | Player", color = Color.White) },
                backgroundColor = Color(0xff0f9d58)
            )
        },
        content = { ViperPlayer(VIDEO_URL) }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViperPlayerTheme {
        MainScreen()
    }
}