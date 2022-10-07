package com.comcast.viperplayer.data

//data class Event(val track: String, val tag: TAG, val position: Int = 0)

data class Analytics(val tag: String)

data class Log(val tag: String, val msg: String)


enum class Event {
    IDLE,
    BUFFERING,
    BEGIN_PLAYBACK,
    END_PLAYBACK,
    PROGRESS_PLAYBACK,
    BITRATE_CHANGE
}

enum class BitRate(rate: Long) {
    DEFAULT(1000),
    LOW(300),
    MEDIUM(500),
    HIGH(1000),
    HD(2000)
}