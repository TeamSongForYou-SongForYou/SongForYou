package com.hanyeop.songforyou.view.circle_view

import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityCircleViewBinding
import com.hanyeop.songforyou.view.karaoke.KaraokeActivity
import com.hanyeop.songforyou.view.next.NextSongRecommendActivity
import com.hanyeop.songforyou.view.random.RandomRecommendActivity
import com.hanyeop.songforyou.view.search.SongSearchActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CircleViewActivity : BaseActivity<ActivityCircleViewBinding>(R.layout.activity_circle_view) {

    override fun init() {
        initClickListener()
        val hyperspaceJump: Animation = AnimationUtils.loadAnimation(this, R.xml.bottom_navi_rotate)
        binding.centerView.startAnimation(hyperspaceJump)

    }

    private fun initClickListener(){
        binding.apply {
            btnNext.setOnClickListener{
                lifecycleScope.launch {
                    for(i in 0 until 90){
                        binding.layoutCircle.angleOffset -= 1
                        delay(3)
                    }
                }
            }
            btnPrevious.setOnClickListener {
                lifecycleScope.launch {
                    for(i in 0 until 90){
                        binding.layoutCircle.angleOffset += 1
                        delay(3)
                    }
                }
            }
            layoutKaraoke.setOnClickListener {
                startActivity(Intent(this@CircleViewActivity, KaraokeActivity::class.java))
                finish()
            }
            layoutRandom.setOnClickListener {
                startActivity(Intent(this@CircleViewActivity, RandomRecommendActivity::class.java))
                finish()
            }
            layoutSearch.setOnClickListener {
                startActivity(Intent(this@CircleViewActivity, SongSearchActivity::class.java))
                finish()
            }
            layoutBefore.setOnClickListener {
                startActivity(Intent(this@CircleViewActivity, NextSongRecommendActivity::class.java))
                finish()
            }
        }
    }
}