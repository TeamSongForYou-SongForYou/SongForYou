package com.hanyeop.songforyou.di

import android.app.Application
import com.hanyeop.songforyou.utils.SharedPreferencesUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {

    companion object {
        lateinit var sharedPreferencesUtil: SharedPreferencesUtil
    }
    override fun onCreate() {
        super.onCreate()

        // shared preference 초기화
        sharedPreferencesUtil = SharedPreferencesUtil(this)

    }

}