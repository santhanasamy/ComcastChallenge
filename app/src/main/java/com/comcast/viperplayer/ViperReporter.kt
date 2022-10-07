package com.comcast.viperplayer

import android.util.Log
import com.comcast.viperplayer.data.ViperAppEvent

/**
 * Viper reporter to report the status of the player through different
 * medium.
 */
interface ViperReporter {
    fun report(event: ViperAppEvent? = ViperAppEvent.PLAYER_ANALYTICS, message: String)
}

/** Reporter to report status through logs. */
object ViperLogReporter : ViperReporter {
    override fun report(event: ViperAppEvent?, message: String) {
        Log.d("Analytics", "[${event?.name ?: ViperAppEvent.PLAYER_ANALYTICS.name}]  [$message]")
    }
}

/** Reporter to report status through any other external analytical tools. */
object ViperAnalyticsReporter : ViperReporter {
    override fun report(event: ViperAppEvent?, message: String) {
    }
}