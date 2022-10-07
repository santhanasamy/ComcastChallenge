package com.comcast.viperplayer.ui.components

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.comcast.viperplayer.ViperAnalyticsCollector
import com.comcast.viperplayer.data.VIDEO_URL
import com.comcast.viperplayer.ui.theme.ViperPlayerTheme

@Composable
@OptIn(UnstableApi::class)
fun ViperPlayer(uri: State<String>, collector: ViperAnalyticsCollector? = null) {

    val videoUri = remember { uri }
    val context = LocalContext.current
    val hlsDataSourceFactory = HlsMediaSource.Factory(
        DefaultDataSourceFactory(context, "ua")
    )

    val viperPlayer = ExoPlayer.Builder(context).apply {
        setMediaSourceFactory(hlsDataSourceFactory)
        if (null != collector) {
            setAnalyticsCollector(collector)
        }
    }.build().apply {
        setMediaItem(MediaItem.fromUri(videoUri.value))
        playWhenReady = true
        prepare()
    }

    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                player = viperPlayer
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        })
    ) {
        onDispose { viperPlayer.release() }
    }
}


@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    ViperPlayerTheme {
        val videoUri: MutableState<String> = remember { mutableStateOf(VIDEO_URL) }
        ViperPlayer(videoUri)
    }
}