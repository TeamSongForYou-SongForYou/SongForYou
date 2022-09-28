package com.hanyeop.songforyou.view.circle_view

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityCircleViewBinding
import com.hanyeop.songforyou.view.karaoke.KaraokeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CircleViewActivity : BaseActivity<ActivityCircleViewBinding>(R.layout.activity_circle_view) {

    override fun init() {
        initClickListener()


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
        }
    }
}