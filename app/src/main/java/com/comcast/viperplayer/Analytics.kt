package com.comcast.viperplayer

import android.os.Looper
import androidx.media3.common.Format
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DecoderCounters
import androidx.media3.exoplayer.DecoderReuseEvaluation
import androidx.media3.exoplayer.analytics.AnalyticsCollector
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.source.MediaSource
import com.comcast.viperplayer.data.Analytics
import com.comcast.viperplayer.data.Event

fun Analytics.post(event: Event) {
    // Event(track = "", tag = TAG.BEGIN_PLAYBACK)
}

fun log(from: String, message: String) {

    println("[$from] :: [$message]")
}

@UnstableApi
class ViperAnalyticsCollector() : AnalyticsCollector {
    override fun onRenderedFirstFrame(output: Any, renderTimeMs: Long) {
        log("onRenderedFirstFrame", "$renderTimeMs")
    }

    override fun onBandwidthSample(elapsedMs: Int, bytesTransferred: Long, bitrateEstimate: Long) {
        log("onBandwidthSample", "$elapsedMs : $bytesTransferred : $bitrateEstimate")
    }

    override fun addListener(listener: AnalyticsListener) {
        log("addListener", "$listener")
    }

    override fun removeListener(listener: AnalyticsListener) {
        log("removeListener", "$listener")
    }

    override fun setPlayer(player: Player, looper: Looper) {
        println("setPlayer [$player]")
    }

    override fun release() {
        log("release", "")
    }

    override fun updateMediaPeriodQueueInfo(
        queue: MutableList<MediaSource.MediaPeriodId>,
        readingPeriod: MediaSource.MediaPeriodId?
    ) {
        log("updateMediaPeriodQueueInfo", "${readingPeriod?.periodUid}")
    }

    override fun notifySeekStarted() {
        log("notifySeekStarted", "")
    }

    override fun onAudioEnabled(counters: DecoderCounters) {
        log("onAudioEnabled", "$counters")
    }

    override fun onAudioDecoderInitialized(
        decoderName: String,
        initializedTimestampMs: Long,
        initializationDurationMs: Long
    ) {
        log("onAudioDecoderInitialized", "")
    }

    override fun onAudioInputFormatChanged(
        format: Format,
        decoderReuseEvaluation: DecoderReuseEvaluation?
    ) {
        log("onAudioInputFormatChanged", "")
    }

    override fun onAudioPositionAdvancing(playoutStartSystemTimeMs: Long) {
        log("onAudioPositionAdvancing", "")
    }

    override fun onAudioUnderrun(
        bufferSize: Int,
        bufferSizeMs: Long,
        elapsedSinceLastFeedMs: Long
    ) {
        log("onAudioUnderrun", "")
    }

    override fun onAudioDecoderReleased(decoderName: String) {
        log("onAudioDecoderReleased", "")
    }

    override fun onAudioDisabled(counters: DecoderCounters) {
        log("onAudioDisabled", "")
    }

    override fun onAudioSinkError(audioSinkError: Exception) {
        log("onAudioSinkError", "")
    }

    override fun onAudioCodecError(audioCodecError: Exception) {
        log("onAudioCodecError", "")
    }

    override fun onVideoEnabled(counters: DecoderCounters) {
        log("onVideoEnabled", "")
    }

    override fun onVideoDecoderInitialized(
        decoderName: String,
        initializedTimestampMs: Long,
        initializationDurationMs: Long
    ) {
        log("onVideoDecoderInitialized", "")
    }

    override fun onVideoInputFormatChanged(
        format: Format,
        decoderReuseEvaluation: DecoderReuseEvaluation?
    ) {
        log("onVideoInputFormatChanged", "")
    }

    override fun onDroppedFrames(count: Int, elapsedMs: Long) {
        log("onDroppedFrames", "")
    }

    override fun onVideoDecoderReleased(decoderName: String) {
        log("onVideoDecoderReleased", "")
    }

    override fun onVideoDisabled(counters: DecoderCounters) {
        log("onVideoDisabled", "")
    }

    override fun onVideoFrameProcessingOffset(totalProcessingOffsetUs: Long, frameCount: Int) {
        log("onVideoFrameProcessingOffset", "")
    }

    override fun onVideoCodecError(videoCodecError: Exception) {
        log("onVideoCodecError", "")
    }

}
