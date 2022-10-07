package com.comcast.viperplayer.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.analytics.AnalyticsListener
import com.comcast.viperplayer.PlayerManager
import com.comcast.viperplayer.ViperAnalyticsCollector
import com.comcast.viperplayer.data.ViperAppEvent
import kotlinx.coroutines.launch

@UnstableApi
class PlayerViewModel(manager: PlayerManager) : ViewModel() {

    private val _videoToPlay = mutableStateOf("")
    val videoToPlay: State<String> = _videoToPlay

    private val _currentTrack = mutableStateOf<Tracks?>(null)


    private val analyticsListener = object : AnalyticsListener {
        override fun onPlayWhenReadyChanged(
            eventTime: AnalyticsListener.EventTime,
            playWhenReady: Boolean,
            reason: Int
        ) {
            manager.getReporter().report(ViperAppEvent.PLAYER_PLAYBACK, "Begin")
        }

        override fun onTracksChanged(eventTime: AnalyticsListener.EventTime, tracks: Tracks) {
            super.onTracksChanged(eventTime, tracks)
            _currentTrack.value = tracks
        }

    }
    val analyticsCollector: ViperAnalyticsCollector by lazy {
        ViperAnalyticsCollector(manager).apply {
            addListener(analyticsListener)
        }
    }


    init {
        registerPlayBack()
    }

    private fun registerPlayBack() {
        viewModelScope.launch {
        }
    }

    fun onBitRateChange(old: Long, new: Long) {
    }

    fun playVideo(videoUrl: String) {
        _videoToPlay.value = videoUrl
    }
}

internal class PlayerViewModelFactory(private val manager: PlayerManager) :
    ViewModelProvider.Factory {
    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            return PlayerViewModel(manager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
