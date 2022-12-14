package com.hanyeop.songforyou.view.detail

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivitySongDetailBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.audio.AudioRecordActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "SongDetailActivity___"
@AndroidEntryPoint
class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail) {

    private val songDetailViewModel by viewModels<SongDetailViewModel>()
    private var songInfo: SongResponse = SongResponse(0,"","","",0,"","")

    override fun init() {
        songInfo = intent.getSerializableExtra(SONG) as SongResponse

        binding.apply {
            song = songInfo
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)

                    val tmp = songInfo.songYoutubeUrl.split("=")
                    val videoId = tmp[1]
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

            tvTitle.isSelected = true
        }
        songDetailViewModel.getLyrics(songInfo.SongSeq)

        initClickListener()

        initViewModelCallBack()
    }

    private fun initClickListener(){
        binding.apply {
            imgDisLike.setOnClickListener {
                songDetailViewModel.songDisLike(songInfo.SongSeq)
            }
            imgAdd.setOnClickListener {
                songDetailViewModel.addSongBox(songInfo.SongSeq)
            }
            btnRecord.setOnClickListener {
                val intent = Intent(this@SongDetailActivity, AudioRecordActivity::class.java)
                intent.putExtra(SONG,songInfo)
                startActivity(intent)
            }
        }
    }

    private fun initViewModelCallBack(){
        songDetailViewModel.successMsgEvent.observe(this){
            showToast(it)
        }
        songDetailViewModel.failMsgEvent.observe(this){
            showToast(it)
        }
        lifecycleScope.launch {
            songDetailViewModel.lyrics.collectLatest {
                binding.tvLyrics.text = it
            }
        }
    }
}