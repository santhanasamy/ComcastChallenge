package com.comcast.viperplayer.data

enum class ViperAppEvent {
    GENERIC,
    PLAYER_PLAYBACK,
    DETAILED_ANALYTICS

}

enum class BitRate(rate: Long) {
    DEFAULT(1000),
    LOW(300),
    MEDIUM(500),
    HIGH(1000),
    HD(2000)
}