package com.hanyeop.songforyou.view.main

import android.content.Intent
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityMainBinding
import com.hanyeop.songforyou.di.ApplicationClass.Companion.sharedPreferencesUtil
import com.hanyeop.songforyou.utils.JWTUtils
import com.hanyeop.songforyou.view.circle_view.CircleViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController : NavController

    override fun init() {
        initNavigation()

        initClickListener()
    }

    private fun initNavigation() {
        // 네비게이션 연결
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()

        // 바텀 네비게이션 연결
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.background = null
    }

    private fun initClickListener(){
        binding.apply {
            fabMain.setOnClickListener {
                startActivity(Intent(this@MainActivity,CircleViewActivity::class.java))
            }
        }
    }
}