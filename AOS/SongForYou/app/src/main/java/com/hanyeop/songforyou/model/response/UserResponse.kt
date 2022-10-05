package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("userSeq") val userSeq: Int,
    @SerializedName("birthday") val userBirthday: Int,                      // 출생년도
    @SerializedName("email") val userEmail: String,                         // 이메일
    @SerializedName("gender") val userGender: String,                       // 성별
    @SerializedName("id") val userId: String,                               // id
    @SerializedName("nickName") val userNickname: String,                   // 별명
    @SerializedName("pass") val userPass: String,
)
