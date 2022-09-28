package com.hanyeop.songforyou.utils

import android.content.SharedPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class XAccessTokenInterceptor @Inject constructor(
    private val sharedPref: SharedPreferences
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        var token = runBlocking {
//            sharedPref.getString(JWT,"")!!
//        }
        // TODO : 임시, JWT 수정되면 위에꺼 주석해제 이거 삭제.
        var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmZpZHRodG4iLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjY0MzYyNDc0fQ.TPXUhhCdaWkR1cPDuP91y6T9sKjoR0hpChyQjE-n04hrgmFikWzdehCjdYhmT0It4kLBQxlKOxfxnhpCUrKLbA"
        val request = chain.request().newBuilder()
            .addHeader(JWT, "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}