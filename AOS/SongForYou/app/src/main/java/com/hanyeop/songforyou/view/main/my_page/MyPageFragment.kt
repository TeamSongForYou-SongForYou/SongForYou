package com.hanyeop.songforyou.view.main.my_page

import android.content.SharedPreferences
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentMyPageBinding
import com.hanyeop.songforyou.di.AppModule_ProvideSharedPreferencesFactory
import com.hanyeop.songforyou.utils.NICKNAME
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

            }
        }

        initClickListener()
    }

    private fun initClickListener(){

    }
}