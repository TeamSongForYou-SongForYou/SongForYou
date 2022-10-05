package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.SongResponse
import retrofit2.http.GET

interface UbRecommendApi {

    @GET("ub-recommend/my-sound")
    suspend fun getUbRecommendMySound(): BaseResponse<List<SongResponse>>
}