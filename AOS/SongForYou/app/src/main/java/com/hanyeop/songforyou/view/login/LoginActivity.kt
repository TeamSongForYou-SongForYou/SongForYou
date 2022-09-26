package com.hanyeop.songforyou.view.login

import android.Manifest
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var navController : NavController
    private var backButtonTime = 0L

    //test
    override fun init() {
        initNavigation()

        initPermission()
    }

    private fun initPermission(){
        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                }
                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    showToast("권한을 허가해주세요.")
                }
            })
            .setDeniedMessage("앱 사용을 위해 권한을 허용으로 설정해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECORD_AUDIO
            )
            .check()
    }
    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_login) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_login)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        val gapTime = currentTime - backButtonTime
//        val currentFragment = supportFragmentManager.findFragmentById(galleryFragment.id)
        //첫 화면(바텀 네비 화면들)이면 뒤로가기 시 앱 종료
        if (navController.currentDestination!!.id == R.id.loginFragment ||
            navController.currentDestination!!.id == R.id.joinFragment
        ) {
            if (gapTime in 0..2000) {
                //2초 안에 두 번 뒤로가기 누를 시 앱 종료
                finishAndRemoveTask()
            } else {
                backButtonTime = currentTime
                Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
        //첫 화면(바텀 네비 화면들)이 아니면
        else {
            //Navigation의 스택에서 pop 됨(원래 동작)
            super.onBackPressed()
        }
    }
}