package com.hanyeop.songforyou.utils

import android.util.Base64
import android.util.Log
import com.hanyeop.songforyou.di.ApplicationClass
import com.hanyeop.songforyou.model.dto.UserDto
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets.UTF_8

private const val TAG = "JWTUtils___"

object JWTUtils {
    @Throws(Exception::class)
    fun decoded(JWTEncoded: String): UserDto? {
        try {
            Log.d(TAG, "decoded: $JWTEncoded")
            val split = JWTEncoded.split(".").toTypedArray()
            val body = getJson(split[1])
            Log.d(TAG, "decoded: $body")

            val userBirthday = JSONObject(body)["userBirthday"] as Int
            val userEmail = JSONObject(body)["userEmail"] as String
            val userGender = JSONObject(body)["userGender"] as String
            val userId = JSONObject(body)["userId"] as String
            val userNickname = JSONObject(body)["userNickname"] as String
            val userPass = JSONObject(body)["userPass"] as String
            val userProfileImgSeq = JSONObject(body)["userProfileImgSeq"] as Int
            val userRegTime = JSONObject(body)["userRegTime"] as String

            return UserDto(
                userBirthday = userBirthday,
                userEmail = userEmail,
                userGender = userGender,
                userId = userId,
                userNickname = userNickname,
                userPass = userPass,
                userProfileImgSeq = userProfileImgSeq,
                userRegTime = userRegTime
            )
        } catch (e: UnsupportedEncodingException) {
            Log.e(TAG, "decoded: $e")
            ApplicationClass.sharedPreferencesUtil.deleteToken()
            return null
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, UTF_8)
    }
}