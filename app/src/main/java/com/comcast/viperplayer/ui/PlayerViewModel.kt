package com.comcast.viperplayer.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.common.util.Clock
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector
import com.comcast.viperplayer.PlayerManager
import com.comcast.viperplayer.data.ViperAppEvent.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@UnstableApi
class PlayerViewModel(manager: PlayerManager) : ViewModel() {

    private val _videoToPlay = mutableStateOf("")
    val videoToPlay: State<String> = _videoToPlay

    private val _currentTrack = mutableStateOf<Tracks?>(null)

    /**
     * Delegate to listen for the Bit rate changes and report the change with
     * the current reporter.
     */
    var bitRateChange: Long by Delegates.observable(0) { _, previous, current ->
        if (previous != current) {
            manager.getReporter()
                .report(PLAYER_BIT_RATE_CHANGE, "($previous) -> ($current)")
        }
    }
    val positionTracker = Channel<Long>(Channel.CONFLATED)

    private val viperAnalyticsListener = object : AnalyticsListener {

        override fun onPlaybackStateChanged(eventTime: AnalyticsListener.EventTime, state: Int) {

            when (state) {
                Player.STATE_IDLE -> {
                    manager.getReporter().report(PLAYER_PLAYBACK, "Playback Idle")
                }
                Player.STATE_READY -> {
                    manager.getReporter().report(PLAYER_PLAYBACK, "Playback Begins")
                }
                Player.STATE_BUFFERING -> {
                    manager.getReporter().report(PLAYER_PLAYBACK, "Playback Buffering")
                }
                Player.STATE_ENDED -> {
                    manager.getReporter().report(PLAYER_PLAYBACK, "Playback End")
                }
            }
        }

        override fun onTracksChanged(eventTime: AnalyticsListener.EventTime, tracks: Tracks) {
            _currentTrack.value = tracks
            manager.getReporter().report(PLAYER_TRACK_CHANGE, "$tracks")
        }

        override fun onIsPlayingChanged(
            eventTime: AnalyticsListener.EventTime,
            isPlaying: Boolean
        ) {
            val state = if (isPlaying) {
                "Player Starts Playing"
            } else {
                "Player Stops Playing"
            }

            manager.getReporter().report(PLAYER_PLAYBACK, state)
        }

        override fun onPlaybackParametersChanged(
            eventTime: AnalyticsListener.EventTime,
            playbackParameters: PlaybackParameters
        ) {
            super.onPlaybackParametersChanged(eventTime, playbackParameters)
        }

        /**
         * Track Bandwidth changes
         *
         * @param eventTime
         * @param totalLoadTimeMs
         * @param totalBytesLoaded
         * @param bitrateEstimate
         */
        override fun onBandwidthEstimate(
            eventTime: AnalyticsListener.EventTime,
            totalLoadTimeMs: Int,
            totalBytesLoaded: Long,
            bitrateEstimate: Long
        ) {
            bitRateChange = totalBytesLoaded
        }

        /**
         * Report all the events.
         *
         * @param player
         * @param events
         */
        override fun onEvents(player: Player, events: AnalyticsListener.Events) {
            if (manager.DEBUG) {
                manager.getReporter().report(PLAYER_ANALYTICS, "$events")
            }
        }

    }
    val analyticsCollector: DefaultAnalyticsCollector by lazy {
        DefaultAnalyticsCollector(Clock.DEFAULT).apply {
            addListener(viperAnalyticsListener)
        }
    }

    init {
        viewModelScope.launch {
            for (position in positionTracker) {
                manager.getReporter().report(PLAYER_TRACK_POSITION, "$position")
            }
        }
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
