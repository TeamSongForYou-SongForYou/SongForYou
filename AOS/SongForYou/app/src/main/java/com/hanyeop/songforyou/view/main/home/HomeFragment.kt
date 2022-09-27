package com.hanyeop.songforyou.view.main.home

import androidx.fragment.app.viewModels
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun init() {
        // TODO : 테스트 코드. 지워야함
//        homeViewModel.getIbRecommendBefore(1)
    }

    private fun initViewModelCallBack(){

    }
}