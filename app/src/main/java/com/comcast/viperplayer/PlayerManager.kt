package com.comcast.viperplayer

object PlayerManager {

    /** Local configuration flag to decide the reporting strategy */
    private const val isLogReporter = true


    /** Reporter configured with the media player */
    fun getReporter(): ViperReporter {
        return if (isLogReporter) {
            ViperLogReporter
        } else {
            ViperAnalyticsReporter
        }
    }
}