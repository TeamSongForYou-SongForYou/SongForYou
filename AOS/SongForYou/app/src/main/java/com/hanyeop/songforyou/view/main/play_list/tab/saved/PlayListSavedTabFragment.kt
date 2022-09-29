package com.hanyeop.songforyou.view.main.play_list.tab.saved

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentPlayListSavedTabBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.detail.SongDetailActivity
import com.hanyeop.songforyou.view.main.home.SongDetailListener
import com.hanyeop.songforyou.view.main.play_list.PlayListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayListSavedTabFragment : BaseFragment<FragmentPlayListSavedTabBinding>(R.layout.fragment_play_list_saved_tab) {

    private val playListViewModel by viewModels<PlayListViewModel>()
    private lateinit var adapter: SavedAdapter

    override fun init() {
        adapter = SavedAdapter(songDetailListener)
        binding.apply {
            recyclerMyList.adapter = adapter
        }

        initViewModelCallBack()

        playListViewModel.getSongList()
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            playListViewModel.songList.collectLatest {
                Log.d("test5", "----------------------: $it")
                adapter.submitList(it)
            }
        }
    }

    private val songDetailListener = object: SongDetailListener{
        override fun onItemClick(song: SongResponse) {
            val intent = Intent(context, SongDetailActivity::class.java)
            intent.putExtra(SONG,song)
            startActivity(intent)
        }
    }
}
