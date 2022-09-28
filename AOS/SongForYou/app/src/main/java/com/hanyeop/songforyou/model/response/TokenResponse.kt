package com.hanyeop.songforyou.model.response

data class TokenResponse(
    val grantType: String,
    val accessToken:String,
    val refreshToken: String,
    val accessTokenExpiresIn: Int
)
