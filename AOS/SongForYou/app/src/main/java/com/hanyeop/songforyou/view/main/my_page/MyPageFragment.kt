package com.hanyeop.songforyou.view.main.my_page

import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentMyPageBinding
import com.hanyeop.songforyou.di.AppModule_ProvideSharedPreferencesFactory
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.utils.NICKNAME
import com.hanyeop.songforyou.utils.RECORD
import com.hanyeop.songforyou.view.audio_play.AudioPlayActivity
import com.hanyeop.songforyou.view.main.play_list.PlayListViewModel
import com.hanyeop.songforyou.view.main.play_list.tab.record.RecordAdapter
import com.hanyeop.songforyou.view.main.play_list.tab.record.RecordListener
import com.hanyeop.songforyou.view.user_voice.UserVoiceActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val playListViewModel by viewModels<PlayListViewModel>()
    private lateinit var adapter: RecordAdapter

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun init() {
        adapter = RecordAdapter(recordListener)

        binding.apply {
            tvNickName.text = sharedPreferences.getString(NICKNAME, "잠만보")

            recyclerRecord.adapter = adapter

            btnRecord.setOnClickListener {
                val intent = Intent(context, UserVoiceActivity::class.java)
                startActivity(intent)
            }
        }
        initViewModelCallBack()

        playListViewModel.getRecordList()

        initClickListener()
    }

    private fun initClickListener(){

    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            playListViewModel.recordList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private val recordListener = object : RecordListener {
        override fun onItemClick(record: RecordResponse) {
            val intent = Intent(context, AudioPlayActivity::class.java)
            intent.putExtra(RECORD, record)
            startActivity(intent)
        }
    }
}