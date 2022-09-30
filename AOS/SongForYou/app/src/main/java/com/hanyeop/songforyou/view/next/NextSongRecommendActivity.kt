package com.hanyeop.songforyou.view.next

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityNextSongRecommendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NextSongRecommendActivity : BaseActivity<ActivityNextSongRecommendBinding>(R.layout.activity_next_song_recommend) {

    private lateinit var navController : NavController

    override fun init() {
        initNavigation()
    }

    private fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_next) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_next)
    }
}