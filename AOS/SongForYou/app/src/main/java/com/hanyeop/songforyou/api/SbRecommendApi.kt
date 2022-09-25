package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SbRecommendApi {

    @GET("sb-recommend/list")
    suspend fun getSbRecommend(
        @Query ("genre") genre: String,
        @Query ("age") age: Int,
        @Query ("gender") gender: String,
        @Query ("weather") weather: Int
    ): BaseResponse<List<SongResponse>>

    @GET("sb-recommend/random-list")
    suspend fun getSbRecommendRandom(): BaseResponse<List<SongResponse>>

}