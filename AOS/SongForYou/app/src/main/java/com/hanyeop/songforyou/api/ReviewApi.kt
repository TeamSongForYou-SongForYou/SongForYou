package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewApi {
    @GET("review/list")
    suspend fun getReview(
        @Query ("name") name: String,
        @Query ("address") address: String
    ): BaseResponse<List<ReviewResponse>>
}