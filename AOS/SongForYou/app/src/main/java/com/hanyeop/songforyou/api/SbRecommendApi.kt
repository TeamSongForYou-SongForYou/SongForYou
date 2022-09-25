package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecommendResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SbRecommendApi {

    @GET("sb-recommend/list")
    suspend fun getSbRecommend(
        @Query ("genre") genre: String,
        @Query ("age") age: Int,
        @Query ("gender") gender: String,
        @Query ("weather") weather: Int
    ): BaseResponse<List<RecommendResponse>>
}