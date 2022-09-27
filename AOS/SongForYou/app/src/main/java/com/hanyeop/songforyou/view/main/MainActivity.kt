package com.hanyeop.songforyou.view.main

import android.Manifest
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityMainBinding
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
    }

    private fun initClickListener(){
        binding.apply {
            fabMain.setOnClickListener {
                startActivity(Intent(this@MainActivity,CircleViewActivity::class.java))
            }
        }
    }
}