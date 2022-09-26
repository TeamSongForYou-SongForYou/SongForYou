package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.LyricsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SongApi {

    @GET("song/{songSeq}/detail")
    suspend fun getSongDetail(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<SongDetailResponse>

    @GET("song/{songSeq}/lyrics")
    suspend fun getLyrics(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<LyricsResponse>
}