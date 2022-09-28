package com.hanyeop.songforyou.view.search

import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivitySongSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SongSearchActivity : BaseActivity<ActivitySongSearchBinding>(R.layout.activity_song_search) {

    private lateinit var songSearchAdapter: SongSearchAdapter
    private val songSearchViewModel by viewModels<SongSearchViewModel>()

    override fun init() {
        songSearchAdapter = SongSearchAdapter()

        binding.apply {
            recyclerSearch.adapter = songSearchAdapter
        }
        initViewModelCallBack()
        
        initSearchView()
    }
    
    private fun initSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                songSearchViewModel.songSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            songSearchViewModel.resultList.collectLatest {
                songSearchAdapter.submitList(it)
                binding.tvCount.text = it.size.toString()
            }
        }
    }
}