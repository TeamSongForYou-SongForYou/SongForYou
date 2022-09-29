package com.hanyeop.songforyou.view.main.play_list.tab.record

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentPlayListRecordTabBinding
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.utils.RECORD
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.audio_play.AudioPlayActivity
import com.hanyeop.songforyou.view.detail.SongDetailActivity
import com.hanyeop.songforyou.view.main.play_list.PlayListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayListRecordTabFragment : BaseFragment<FragmentPlayListRecordTabBinding>(R.layout.fragment_play_list_record_tab) {

    private val playListViewModel by viewModels<PlayListViewModel>()
    private lateinit var adapter: RecordAdapter

    override fun init() {
        adapter = RecordAdapter(recordListener)
        binding.apply {
            recyclerRecord.adapter = adapter
        }

        initViewModelCallBack()

        playListViewModel.getRecordList()
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            playListViewModel.recordList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private val recordListener = object : RecordListener{
        override fun onItemClick(record: RecordResponse) {
            val intent = Intent(context, AudioPlayActivity::class.java)
            intent.putExtra(RECORD, record)
            startActivity(intent)
        }
    }
}