package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IbRecommendApi {

    @GET("/ib-recommend/my-list")
    suspend fun getIbRecommendMyList() : BaseResponse<List<SongResponse>>

    @GET("ib-recommend/my-record")
    suspend fun getIbRecommendMyRecord(
        @Query ("datelimit") dateLimit: Int
    ): BaseResponse<List<SongResponse>>

    @GET("ib-recommend/{songSeq}/before-after")
    suspend fun getIbRecommendBefore(
        @Path("songSeq") songSeq: Int
    ): BaseResponse<List<SongResponse>>
}