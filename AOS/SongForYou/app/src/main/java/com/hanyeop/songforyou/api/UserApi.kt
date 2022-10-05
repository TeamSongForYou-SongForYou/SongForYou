package com.hanyeop.songforyou.api

import android.provider.ContactsContract.CommonDataKinds.Email
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.model.response.OauthResponse
import com.hanyeop.songforyou.model.response.TokenResponse
import com.hanyeop.songforyou.utils.JWT
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    // 일반 회원가입
    @POST("/user/signUp")
    suspend fun signUpUser(
        @Body userDto: UserDto
    ): BaseResponse<UserDto>

    // 일반 로그인
    @POST("/user/login")
    suspend fun loginUser(
        @Body map: HashMap<String, String>
    ): BaseResponse<TokenResponse>

    // 이메일 중복 검사
    @GET("/user/isUsedEmail")
    suspend fun checkEmail(
        @Query("email") email: String
    ): BaseResponse<String>

    // 닉네임 중복 검사
    @GET("/user/isUsednickName")
    suspend fun checkNickname(
        @Query("nickName") nickName: String
    ): BaseResponse<String>

    // 이메일 인증번호 전송
    @GET("/user/emailAuth")
    suspend fun requestEmailAuth(
        @Query("email") userEmail: String
    ): BaseResponse<String>


    // 비밀번호 찾기
    @PUT("/user/newPass/{email}")
    suspend fun findPassword(
        @Body map: HashMap<String, String>
    ): BaseResponse<String>

    @GET("login/oauth2/code/google")
    suspend fun googleLogin(@Query("code") code: String): OauthResponse
}