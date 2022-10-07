package com.comcast.viperplayer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comcast.viperplayer.data.Event
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaybackViewModel : ViewModel() {

    val playerState = Channel<Event>(Channel.CONFLATED)
    private val _state = MutableStateFlow<Event>(Event.IDLE)
    val state: StateFlow<Event> = _state

    init {
        registerPlayBack()
    }

    private fun registerPlayBack() {

        viewModelScope.launch {

        }
    }

    fun onBitRateChange(old: Long, new: Long) {


    }

}