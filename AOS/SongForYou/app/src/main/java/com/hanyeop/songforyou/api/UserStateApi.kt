package com.hanyeop.songforyou.api

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.UserResponse
import retrofit2.http.GET

interface UserStateApi {

    @GET("user-state/information")
    suspend fun getUserInfo(): BaseResponse<UserResponse>
}