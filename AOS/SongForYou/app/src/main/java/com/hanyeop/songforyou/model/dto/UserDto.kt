package com.hanyeop.songforyou.model.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    //@SerializedName("userSeq") val userSeq: Int,
    //@SerializedName("userVoiceFileSeq") val userVoiceFileSeq: Int,
    @SerializedName("birthday") val userBirthday: Int,                      // 출생년도
    @SerializedName("email") val userEmail: String,                         // 이메일
    @SerializedName("gender") val userGender: String,                       // 성별
    @SerializedName("id") val userId: String,                               // id
    @SerializedName("nickname") val userNickname: String,                   // 별명
    @SerializedName("pass") val userPass: String,                           // 비밀번호
    @SerializedName("profileImgSeq") val userprofileImgSeq: Int,            // 프로필
    @SerializedName("regTime") val userRegTime: String,                     // 등록시간
)
// id, 프로필 이미지 번호 왜 필요한지 물어보기