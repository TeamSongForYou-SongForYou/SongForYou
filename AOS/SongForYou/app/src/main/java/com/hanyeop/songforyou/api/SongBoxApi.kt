package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface SongBoxApi {

    @POST("song-box/my-box/{songSeq}")
    suspend fun addSongBox(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<String>
}