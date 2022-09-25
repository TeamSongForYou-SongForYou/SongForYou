package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecommendResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IbRecommendApi {

    @GET("ib-recommend/my-record")
    suspend fun getIbRecommendMyRecord(
        @Query ("datelimit") dateLimit: Int
    ): BaseResponse<RecommendResponse>
}