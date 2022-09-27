package com.ssafy.heritage.util

import android.content.Context
import android.content.SharedPreferences
import com.ssafy.heritage.data.dto.User

class SharedPreferencesUtil(context: Context) {
    private val sharedPreferencesName = "store_preference"
    private val preferences: SharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    // 사용자 정보 저장
    fun saveUser(user: User){
        val editor = preferences.edit()
        user.userSeq?.let { editor.putInt("userSeq", it) }
        editor.putString("userNickName",user.userNickname)
        editor.commit()
    }

    // 사용자 정보 불러오기
    fun getUser(): Int {
        return preferences.getInt("userSeq", 0)
    }

    // 사용자 정보 불러오기
    fun getUserNickName(): String? {
        return preferences.getString("userNickName", "")
    }
    // 토큰 저장
    fun saveToken(token: String) {
        val editor = preferences.edit()
        editor.putString("token", token)
        editor.commit()
    }

    // 토큰 불러오기
    fun getToken(): String? {
        val token = preferences.getString("token", null)
        return token
    }

    // preference 지우기
    fun deleteToken() {
        val editor = preferences.edit()
        editor.remove("token")
        editor.apply()
    }
}