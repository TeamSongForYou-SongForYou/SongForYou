package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.model.response.SongWithWordResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SbRecommendApi {

    @GET("sb-recommend/list")
    suspend fun getSbRecommend(
        @Query ("genre") genre: String,
        @Query ("age") age: Int,
        @Query ("gender") gender: String,
        @Query ("weather") weather: Int
    ): BaseResponse<List<SongResponse>>

    @GET("sb-recommend/list")
    suspend fun getWeatherRecommend(
        @Query ("weather") weather: Int
    ): BaseResponse<List<SongResponse>>

    @GET("sb-recommend/list")
    suspend fun getAgeRecommend(
        @Query ("age") age: Int
    ): BaseResponse<List<SongResponse>>

    @GET("sb-recommend/random-list")
    suspend fun getSbRecommendRandom(): BaseResponse<List<SongResponse>>

    @GET("sb-recommend/{listNum}/recommend-list")
    suspend fun getRecommendWithWord(
        @Path ("listNum") listNum: Int
    ): BaseResponse<SongWithWordResponse>
}