package com.hanyeop.songforyou.view.random

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityRandomRecommendBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.audio.AudioRecordActivity
import com.hanyeop.songforyou.view.detail.SongDetailViewModel
import com.hanyeop.songforyou.view.main.home.HomeViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomRecommendActivity : BaseActivity<ActivityRandomRecommendBinding>(R.layout.activity_random_recommend) {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val songDetailViewModel by viewModels<SongDetailViewModel>()
    private var songInfo: SongResponse = SongResponse(0,"","","",0,"","")

    override fun init() {

        binding.apply {
            tvTitle.isSelected = true
        }

        initViewModelCallback()
        initClickListener()

        homeViewModel.getSbRecommendRandomList()
    }

    private fun initViewModelCallback() = with(binding){
        lifecycleScope.launch {
            homeViewModel.recommendRandomList.collectLatest {
                if(it.isNotEmpty()) {
                    songInfo = it[0]
                    binding.song = songInfo

                    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)

                            val tmp = songInfo.songYoutubeUrl.split("=")
                            val videoId = tmp[1]
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })

                    songDetailViewModel.getLyrics(songInfo.SongSeq)
                }
            }
        }
        songDetailViewModel.successMsgEvent.observe(this@RandomRecommendActivity){
            showToast(it)
        }
        songDetailViewModel.failMsgEvent.observe(this@RandomRecommendActivity){
            showToast(it)
        }
        lifecycleScope.launch {
            songDetailViewModel.lyrics.collectLatest {
                binding.tvLyrics.text = it
            }
        }
    }

    private fun initClickListener() = with(binding){
        imgDisLike.setOnClickListener {
            songDetailViewModel.songDisLike(songInfo.SongSeq)
        }
        imgAdd.setOnClickListener {
            songDetailViewModel.addSongBox(songInfo.SongSeq)
        }
        btnRecord.setOnClickListener {
            val intent = Intent(this@RandomRecommendActivity, AudioRecordActivity::class.java)
            intent.putExtra(SONG,songInfo)
            startActivity(intent)
        }
    }
}