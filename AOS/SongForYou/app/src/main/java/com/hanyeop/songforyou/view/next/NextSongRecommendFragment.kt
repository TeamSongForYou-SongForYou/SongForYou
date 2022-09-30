package com.hanyeop.songforyou.view.next

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentNextSongRecommendBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.detail.SongDetailActivity
import com.hanyeop.songforyou.view.search.SongSearchAdapter
import com.hanyeop.songforyou.view.search.SongSearchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NextSongRecommendFragment : BaseFragment<FragmentNextSongRecommendBinding>(R.layout.fragment_next_song_recommend) {

    private lateinit var songSearchAdapter: SongSearchAdapter
    private val nextSongRecommendViewModel by activityViewModels<NextSongRecommendViewModel>()

    override fun init() {
        songSearchAdapter = SongSearchAdapter(songSearchListener)
        binding.recyclerNextRecommend.adapter = songSearchAdapter

        initView()
    }

    private fun initView() = with(binding){
        // 이전 곡 이름
        tvBeforeSongTitle.text = nextSongRecommendViewModel.beforeSongTitle.value

        // 첫번째 추천 곡
        val list : MutableList<SongResponse>
        = nextSongRecommendViewModel.nextSongRecommendList.value as MutableList<SongResponse>
        Glide.with(this@NextSongRecommendFragment)
            .load(list [0].songThumbnailUrl)
            .into(imgSong)
        tvTitle.text = list [0].SongTitle
        tvNextSongTitle.text = list [0].SongTitle
        tvArtist.text = list [0].SongArtistName

        // 그 외 추천 리스트
        list.removeAt(0)
        songSearchAdapter.submitList(list)
    }

    private val songSearchListener = object : SongSearchListener {
        override fun onItemClick(song: SongResponse) {
            Log.d("test5", "onItemClick: $song")
            val intent = Intent(context,  SongDetailActivity::class.java)
            intent.putExtra(SONG,song)
            startActivity(intent)
        }
    }
}