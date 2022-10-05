package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.ReviewResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ReviewApi {
    
    @GET("review/list")
    suspend fun getReview(
        @Query ("karaokeName") name: String,
        @Query ("karaokeAddress") address: String
    ): BaseResponse<List<ReviewResponse>>

    @Multipart
    @POST("review/upload")
    suspend fun uploadReview(
        @Part("reviewUploadDto") review: RequestBody,
        @Part imgFile : MultipartBody.Part
    ): BaseResponse<ReviewResponse>
}