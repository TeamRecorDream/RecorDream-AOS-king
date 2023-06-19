package com.recodream_aos.recordream.presentation.record.recording // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Timer
import kotlin.concurrent.timer

class RecordBottomSheetViewModel : ViewModel() {
    private var _nowTime = MutableStateFlow(ZERO)
    val nowTime: StateFlow<Int> get() = _nowTime

    private var _replayTime = MutableStateFlow(ZERO)
    val replayTime: StateFlow<Int> get() = _replayTime

    private var _fullProgressBar = MutableStateFlow(false)
    val fullProgressBar: StateFlow<Boolean> get() = _fullProgressBar

    private var _recordingTime: Int = 0
    val recordingTime get() = _recordingTime

    private var _playButtonState :MutableStateFlow<>

    private var firstTimer: Timer? = null
    private var replayTimer: Timer? = null
    private var realTimer: Timer? = null

    fun setFullProgressBarFalse() {
        _fullProgressBar.value = false
    }

    fun initProgressBar() {
        firstTimer = timer(period = ONE_PERCENT, initialDelay = ONE_PERCENT) {
            if (_nowTime.value > HUNDRED_PERCENT) cancel()
            ++_nowTime.value
        }
        initRealTimer()
    }

    fun stopProgressBar() {
        firstTimer?.cancel()
        replayTimer?.cancel()
        realTimer?.cancel()
    }

    fun clearProgressBar() {
        _nowTime.value = ZERO
        _recordingTime = ZERO
    }

    fun setFullProgressBar() {
        _nowTime.value = HUNDRED_PERCENT
    }

    fun replayProgressBar() {
        replayTimer = timer(period = _recordingTime.convertMilliseconds() / PERCENTAGE) {
            if (_replayTime.value > HUNDRED_PERCENT) {
                cancel()
                _fullProgressBar.value = true
            }
            ++_replayTime.value
        }
    }

    fun clearReplayProgressBar() {
        _replayTime.value = ZERO
    }

    private fun Int.convertMilliseconds(): Long = this * ONE_SECOND

    private fun initRealTimer() {
        realTimer = timer(period = ONE_SECOND, initialDelay = ONE_SECOND) {
            _recordingTime++
            print(_recordingTime)
        }
    }

    fun stopReplayProgressBar() {
        replayTimer?.cancel()
    }

    companion object {
        private const val ONE_PERCENT = 1800L
        private const val ZERO = 0
        private const val HUNDRED_PERCENT = 100
        private const val ONE_SECOND: Long = 1000
        private const val PERCENTAGE = 100
    }
}
