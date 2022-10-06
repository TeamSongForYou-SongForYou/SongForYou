package com.hanyeop.songforyou.view.circle_view

import android.content.Intent
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.ViewTreeObserver
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
import java.lang.Math.atan2
private const val TAG = "CircleViewActivity___"
class CircleViewActivity : BaseActivity<ActivityCircleViewBinding>(R.layout.activity_circle_view) {

    override fun init() {
        initClickListener()
        val hyperspaceJump: Animation = AnimationUtils.loadAnimation(this, R.xml.bottom_navi_rotate)
        binding.centerView.startAnimation(hyperspaceJump)

        val center = PointF()
        binding.viewTouch.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                center.x = binding.viewTouch.x + binding.viewTouch.width / 2
                center.y = binding.viewTouch.y + binding.viewTouch.height / 2

                binding.layoutCircle.x = center.x - binding.layoutCircle.width / 2
                binding.layoutCircle.y = center.y - binding.layoutCircle.height / 2

//                binding.layoutSearch.x = center.x - binding.layoutSearch.width / 2
//                binding.layoutSearch.y = center.y - binding.layoutSearch.height / 2

                binding.viewTouch.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        var prevDegree = 0.0

        binding.layoutCircle.setOnTouchListener { v, event ->

            val dX = event.x - center.x.toDouble()
            val dY = event.y - center.y.toDouble()

            var degree = Math.toDegrees(atan2(dY, dX))

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    prevDegree = degree
                }
                MotionEvent.ACTION_MOVE -> {
                    if(prevDegree>degree){
                        lifecycleScope.launch {
                            for(i in 0 until (prevDegree- degree).toInt()){
                                binding.layoutCircle.angleOffset += 1
                            }
                            delay(3)
                        }

                    }else{
                        lifecycleScope.launch {
                            for(i in 0 until (degree - prevDegree).toInt()){
                                binding.layoutCircle.angleOffset -= 1
                            }
                            delay(3)
                        }

                    }
                    prevDegree = degree

                }
            }
            return@setOnTouchListener true
        }
    }
    private fun initClickListener(){
        binding.apply {
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
            frameLayoutClose.setOnClickListener {
                finish()
            }
        }
    }
}