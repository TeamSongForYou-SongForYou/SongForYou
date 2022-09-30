package com.hanyeop.songforyou.view.next

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentNextSongRecommendBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.detail.SongDetailActivity
import com.hanyeop.songforyou.view.search.SongSearchAdapter
import com.hanyeop.songforyou.view.search.SongSearchListener
import com.hanyeop.songforyou.view.search.SongSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NextSongRecommendFragment : BaseFragment<FragmentNextSongRecommendBinding>(R.layout.fragment_next_song_recommend) {

    private lateinit var songSearchAdapter: SongSearchAdapter
    private val songSearchViewModel by viewModels<SongSearchViewModel>()

    override fun init() {
        songSearchAdapter = SongSearchAdapter(songSearchListener)
        binding.apply {
            recyclerSearch.adapter = songSearchAdapter
        }
        initViewModelCallBack()
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            songSearchViewModel.resultList.collectLatest {
                //songSearchAdapter.submitList(it)
                //binding.tvCount.text = it.size.toString()
                binding.tvTitle.text = it[0].SongTitle
                binding.tvNextSongTitle.text = it[0].SongTitle
                binding.tvArtist.text = it[0].SongArtistName
            }
        }
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