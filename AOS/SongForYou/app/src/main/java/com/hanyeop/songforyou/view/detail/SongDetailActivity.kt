package com.hanyeop.songforyou.view.detail

import androidx.activity.viewModels
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivitySongDetailBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

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
                    youTubePlayer.loadVideo(tmp[1],0F)
                }
            })
        }

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
        }
    }

    private fun initViewModelCallBack(){
        songDetailViewModel.successMsgEvent.observe(this){
            showToast(it)
        }
        songDetailViewModel.failMsgEvent.observe(this){
            showToast(it)
        }
    }
}