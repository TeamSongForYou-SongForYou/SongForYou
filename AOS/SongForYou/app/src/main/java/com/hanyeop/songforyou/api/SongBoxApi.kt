package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.MyListResponse
import com.hanyeop.songforyou.model.response.RecordResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface SongBoxApi {

    @POST("song-box/my-box/{songSeq}")
    suspend fun addSongBox(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<String>

    @DELETE("song-box/my-box/{songSeq}")
    suspend fun deleteSongBox(
        @Path ("songSeq") songSeq: Int
    ): BaseResponse<String>

    @GET("song-box/my-record")
    suspend fun getRecordList(): BaseResponse<List<RecordResponse>>

    @Multipart
    @POST("song-box/my-record/{songSeq}")
    suspend fun uploadRecord(
        @Path ("songSeq") songSeq: Int,
        @Part recordFile : MultipartBody.Part
    ): BaseResponse<RecordResponse>

    @GET("song-box/my-box")
    suspend fun getSongList(): BaseResponse<List<MyListResponse>>

}