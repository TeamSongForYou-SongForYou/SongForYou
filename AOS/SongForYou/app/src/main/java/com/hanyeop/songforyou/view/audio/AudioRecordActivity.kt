package com.hanyeop.songforyou.view.audio

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.widget.Button
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityAudioRecordBinding
import com.hanyeop.songforyou.utils.State

class AudioRecordActivity : BaseActivity<ActivityAudioRecordBinding>(R.layout.activity_audio_record) {

    private val soundVisualizerView: SoundVisualizerView by lazy {
        findViewById(R.id.soundVisualizerView)
    }

    private val recordTimeTextView: CountUpView by lazy {
        findViewById(R.id.recordTimeTextView)
    }

    private val resetButton: Button by lazy {
        findViewById(R.id.resetButton)
    }

    private val recordButton: RecordButton by lazy {
        findViewById(R.id.recordButton)
    }
    
    private val recordingFilePath: String by lazy {
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }
    private var state = State.BEFORE_RECORDING
        set(value) { // setter 설정
            field = value // 실제 프로퍼티에 대입
            resetButton.isEnabled = (value == State.AFTER_RECORDING || value == State.ON_PLAYING)
            recordButton.updateIconWithState(value)
        }

    private var recorder: MediaRecorder? = null // 사용 하지 않을 때는 메모리해제 및  null 처리
    private var player: MediaPlayer? = null

    override fun init() {
        initViews()
        bindViews()
        initVariables()
    }

    private fun initViews() {
        recordButton.updateIconWithState(state)
    }

    private fun bindViews() {

        soundVisualizerView.onRequestCurrentAmplitude = {
            recorder?.maxAmplitude ?: 0
        }


        recordButton.setOnClickListener {
            when (state) {
                State.BEFORE_RECORDING -> {
                    startRecoding()
                }
                State.ON_RECORDING -> {
                    stopRecording()
                }
                State.AFTER_RECORDING -> {
                    startPlaying()
                }
                State.ON_PLAYING -> {
                    stopPlaying()
                }
            }
        }

        resetButton.setOnClickListener {
            stopPlaying()
            // clear
            soundVisualizerView.clearVisualization()
            recordTimeTextView.clearCountTime()
            state = State.BEFORE_RECORDING
        }
    }

    private fun initVariables() {
        state = State.BEFORE_RECORDING
    }

    private fun startRecoding() {
        // 녹음 시작 시 초기화
        recorder = MediaRecorder()
            .apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP) // 포멧
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) // 엔코더
                setOutputFile(recordingFilePath) // 우리는 저장 x 캐시에
                prepare()
            }
        recorder?.start()
        recordTimeTextView.startCountup()
        soundVisualizerView.startVisualizing(false)
        state = State.ON_RECORDING
    }

    private fun stopRecording() {
        recorder?.run {
            stop()
            release()
        }
        recorder = null
        soundVisualizerView.stopVisualizing()
        recordTimeTextView.stopCountup()
        state = State.AFTER_RECORDING
    }

    private fun startPlaying() {
        // MediaPlayer
        player = MediaPlayer()
            .apply {
                setDataSource(recordingFilePath)
                prepare() // 재생 할 수 있는 상태 (큰 파일 또는 네트워크로 가져올 때는 prepareAsync() )
            }

        // 전부 재생 했을 때
        player?.setOnCompletionListener {
            stopPlaying()
            state = State.AFTER_RECORDING
        }

        player?.start() // 재생
        recordTimeTextView.startCountup()

        soundVisualizerView.startVisualizing(true)

        state = State.ON_PLAYING
    }

    private fun stopPlaying() {
        player?.release()
        player = null
        soundVisualizerView.stopVisualizing()
        recordTimeTextView.stopCountup()

        state = State.AFTER_RECORDING
    }
}