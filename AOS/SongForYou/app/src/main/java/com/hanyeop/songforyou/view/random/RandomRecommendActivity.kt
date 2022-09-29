package com.hanyeop.songforyou.view.random

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityRandomRecommendBinding
import com.hanyeop.songforyou.view.main.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomRecommendActivity : BaseActivity<ActivityRandomRecommendBinding>(R.layout.activity_random_recommend) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun init() {

        initViewModelCallback()
        initClickListener()

        homeViewModel.getSbRecommendRandomList()
        homeViewModel.getUbRecommendList()
    }

    private fun initViewModelCallback() = with(binding){
        lifecycleScope.launch {
            homeViewModel.recommendRandomList.collectLatest {
                if(it.isNotEmpty()) {
                    Glide.with(this@RandomRecommendActivity)
                        .load(it[0].songThumbnailUrl)
                        .into(imgSong)
                    tvTitle.text = it[0].SongTitle
                    tvArtist.text = it[0].SongArtistName
                }
            }
        }

        lifecycleScope.launch{
            homeViewModel.ubRecommendList.collectLatest {

            }
        }
    }

    private fun initClickListener() = with(binding){

        imgDisLike.setOnClickListener {
            it.setBackgroundResource(R.drawable.thumbs_down_full)

        }

        imgLike.setOnClickListener {
            it.setBackgroundResource(R.drawable.thumbs_up_full)
        }
    }
}