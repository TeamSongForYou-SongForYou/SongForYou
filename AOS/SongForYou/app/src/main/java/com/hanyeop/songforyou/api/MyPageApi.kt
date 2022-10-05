package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecordResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MyPageApi {

    @Multipart
    @POST("my-page/{userSeq}/record")
    suspend fun uploadUserVoice(
        @Path("userSeq") userSeq: Int,
        @Part recordFile : MultipartBody.Part
    ): BaseResponse<Boolean>
}