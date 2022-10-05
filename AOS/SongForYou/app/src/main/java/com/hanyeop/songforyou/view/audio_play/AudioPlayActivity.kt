package com.hanyeop.songforyou.view.audio_play

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityAudioPlayBinding
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.utils.RECORD
import com.hanyeop.songforyou.view.detail.SongDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AudioPlayActivity : BaseActivity<ActivityAudioPlayBinding>(R.layout.activity_audio_play) {

    private val songDetailViewModel by viewModels<SongDetailViewModel>()
    private var simpleExoPlayer: SimpleExoPlayer? = null
    private lateinit var recordInfo: RecordResponse

    override fun init() {
        recordInfo = intent.getSerializableExtra(RECORD) as RecordResponse
        binding.apply {
            record = recordInfo
        }
        Log.d("test5", "init: $recordInfo")
        initViewModelCallBack()

        songDetailViewModel.getLyrics(recordInfo.songDto.SongSeq)

        initPlayer()
    }
    private fun initPlayer(){
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = simpleExoPlayer
        buildMediaSource()?.let {
            simpleExoPlayer?.prepare(it)
        }
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            songDetailViewModel.lyrics.collectLatest {
                binding.tvLyrics.text = it
            }
        }
    }

    // 영상에 출력할 미디어 정보를 가져오는 클래스
    private fun buildMediaSource(): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(recordInfo.fileDto.fileSavedPath))
    }

    // 일시중지
    override fun onResume() {
        super.onResume()
        simpleExoPlayer?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer?.stop()
        simpleExoPlayer?.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer?.release()
    }
}