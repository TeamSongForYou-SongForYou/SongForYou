package com.hanyeop.songforyou.view.main.my_page

import android.content.Intent
import android.content.SharedPreferences
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentMyPageBinding
import com.hanyeop.songforyou.di.AppModule_ProvideSharedPreferencesFactory
import com.hanyeop.songforyou.utils.NICKNAME
import com.hanyeop.songforyou.utils.RECORD
import com.hanyeop.songforyou.view.audio_play.AudioPlayActivity
import com.hanyeop.songforyou.view.user_voice.UserVoiceActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun init() {

        binding.apply {
            tvNickName.text = sharedPreferences.getString(NICKNAME, "잠만보")

            btnRecord.setOnClickListener {
                val intent = Intent(context, UserVoiceActivity::class.java)
                startActivity(intent)
            }
        }

        initClickListener()
    }

    private fun initClickListener(){

    }
}