package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongDetailResponse
import com.hanyeop.songforyou.model.response.UrlResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SongApi {

    @GET("song/{songSeq}/detail")
    suspend fun getSongDetail(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<SongDetailResponse>

    @GET("song/{songSeq}/sound-file")
    suspend fun getSoundFile(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<UrlResponse>
}