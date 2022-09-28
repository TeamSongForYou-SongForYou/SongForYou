package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("grantType") val grantType: String,
    @SerializedName("accessToken") val accessToken:String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("accessTokenExpiresIn") val accessTokenExpiresIn: Long
)
