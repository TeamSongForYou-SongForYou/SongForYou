package com.hanyeop.songforyou.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.core.view.ViewCompat.animate
import androidx.databinding.DataBindingUtil.setContentView
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityIntroBinding
import com.hanyeop.songforyou.databinding.ActivityMainBinding
import com.hanyeop.songforyou.view.login.LoginActivity
import com.hanyeop.songforyou.view.main.MainActivity

class IntroActivity: BaseActivity<ActivityIntroBinding>(R.layout.activity_intro)  {

    override fun init() {
        var dp = this.resources.displayMetrics.density
        binding.ivLogo.animate().translationY(350 * dp).setDuration(2500L)
            .setInterpolator(BounceInterpolator()).start()
        var handler = Handler()
        handler.postDelayed( {

            var intent = Intent( this, LoginActivity::class.java)
            startActivity(intent)
        }, 2500)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}