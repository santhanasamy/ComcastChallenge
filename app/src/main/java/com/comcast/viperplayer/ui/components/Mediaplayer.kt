package com.comcast.viperplayer.ui.components

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.comcast.viperplayer.data.VIDEO_URL
import com.comcast.viperplayer.ui.theme.ViperPlayerTheme

// https://exoplayer.dev/progressive.html

@OptIn(UnstableApi::class)
val dataSourceFactory: DataSource.Factory = DataSource.Factory {
    DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(false)
        .createDataSource().apply {
            setRequestProperty("cookie", "cookieValue")
            setRequestProperty("Range", "1-10000")
        }
}


@Composable
@OptIn(UnstableApi::class)
fun ViperPlayer(uri: String) {

    val context = LocalContext.current

    val viperPlayer = ExoPlayer.Builder(context)
        .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory))
        .build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            playWhenReady = true
            prepare()
        }

    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                player = viperPlayer
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
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
        ViperPlayer(VIDEO_URL)
    }
}