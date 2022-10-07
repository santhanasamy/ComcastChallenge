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
import com.comcast.viperplayer.data.ViperAppEvent


@UnstableApi
class ViperAnalyticsCollector(private val manager: PlayerManager) : AnalyticsCollector {
    private fun sendEvents(from: String, message: String) {
        manager.getReporter().report(ViperAppEvent.DETAILED_ANALYTICS, "[$from] :: [$message]")
    }

    override fun onRenderedFirstFrame(output: Any, renderTimeMs: Long) {
        sendEvents("onRenderedFirstFrame", "$renderTimeMs")
    }

    override fun onBandwidthSample(elapsedMs: Int, bytesTransferred: Long, bitrateEstimate: Long) {
        sendEvents("onBandwidthSample", "$elapsedMs : $bytesTransferred : $bitrateEstimate")
    }

    override fun addListener(listener: AnalyticsListener) {
        sendEvents("addListener", "$listener")
    }

    override fun removeListener(listener: AnalyticsListener) {
        sendEvents("removeListener", "$listener")
    }

    override fun setPlayer(player: Player, looper: Looper) {
        sendEvents("setPlayer [$player]", "")
    }

    override fun release() {
        sendEvents("release", "")
    }

    override fun updateMediaPeriodQueueInfo(
        queue: MutableList<MediaSource.MediaPeriodId>,
        readingPeriod: MediaSource.MediaPeriodId?
    ) {
        sendEvents("updateMediaPeriodQueueInfo", "${readingPeriod?.periodUid}")
    }

    override fun notifySeekStarted() {
        sendEvents("notifySeekStarted", "")
    }

    override fun onAudioEnabled(counters: DecoderCounters) {
        sendEvents("onAudioEnabled", "$counters")
    }

    override fun onAudioDecoderInitialized(
        decoderName: String,
        initializedTimestampMs: Long,
        initializationDurationMs: Long
    ) {
        sendEvents("onAudioDecoderInitialized", "")
    }

    override fun onAudioInputFormatChanged(
        format: Format,
        decoderReuseEvaluation: DecoderReuseEvaluation?
    ) {
        sendEvents("onAudioInputFormatChanged", "")
    }

    override fun onAudioPositionAdvancing(playoutStartSystemTimeMs: Long) {
        sendEvents("onAudioPositionAdvancing", "")
    }

    override fun onAudioUnderrun(
        bufferSize: Int,
        bufferSizeMs: Long,
        elapsedSinceLastFeedMs: Long
    ) {
        sendEvents("onAudioUnderrun", "")
    }

    override fun onAudioDecoderReleased(decoderName: String) {
        sendEvents("onAudioDecoderReleased", "")
    }

    override fun onAudioDisabled(counters: DecoderCounters) {
        sendEvents("onAudioDisabled", "")
    }

    override fun onAudioSinkError(audioSinkError: Exception) {
        sendEvents("onAudioSinkError", "")
    }

    override fun onAudioCodecError(audioCodecError: Exception) {
        sendEvents("onAudioCodecError", "")
    }

    override fun onVideoEnabled(counters: DecoderCounters) {
        sendEvents("onVideoEnabled", "")
    }

    override fun onVideoDecoderInitialized(
        decoderName: String,
        initializedTimestampMs: Long,
        initializationDurationMs: Long
    ) {
        sendEvents("onVideoDecoderInitialized", "")
    }

    override fun onVideoInputFormatChanged(
        format: Format,
        decoderReuseEvaluation: DecoderReuseEvaluation?
    ) {
        sendEvents("onVideoInputFormatChanged", "")
    }

    override fun onDroppedFrames(count: Int, elapsedMs: Long) {
        sendEvents("onDroppedFrames", "")
    }

    override fun onVideoDecoderReleased(decoderName: String) {
        sendEvents("onVideoDecoderReleased", "")
    }

    override fun onVideoDisabled(counters: DecoderCounters) {
        sendEvents("onVideoDisabled", "")
    }

    override fun onVideoFrameProcessingOffset(totalProcessingOffsetUs: Long, frameCount: Int) {
        sendEvents("onVideoFrameProcessingOffset", "")
    }

    override fun onVideoCodecError(videoCodecError: Exception) {
        sendEvents("onVideoCodecError", "")
    }

}
