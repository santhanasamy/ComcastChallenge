package com.comcast.viperplayer

import android.util.Log
import com.comcast.viperplayer.data.ViperAppEvent

interface ViperReporter {
    fun report(event: ViperAppEvent? = ViperAppEvent.GENERIC, message: String)
}

object ViperLogReporter : ViperReporter {
    override fun report(event: ViperAppEvent?, message: String) {
        Log.d(event?.name ?: ViperAppEvent.GENERIC.name, "$message")
    }
}

object ViperAnalyticsReporter : ViperReporter {
    override fun report(event: ViperAppEvent?, message: String) {
    }
}