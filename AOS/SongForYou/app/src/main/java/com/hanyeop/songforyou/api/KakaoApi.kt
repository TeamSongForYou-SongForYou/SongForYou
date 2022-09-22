package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.model.response.ResultSearchKeyword
import com.hanyeop.songforyou.utils.AUTH_HEADER
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApi {
    @GET("v2/local/search/keyword.json") // Keyword.json의 정보를 받아옴
    fun getSearchKeyword(
        @Header("Authorization") apiKey: String = AUTH_HEADER,
        @Query("query") query: String
    ): ResultSearchKeyword
}