package com.hanyeop.songforyou.usecase.oauth2

import com.hanyeop.songforyou.repository.Oauth2Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleLoginUseCase @Inject constructor(
    private val oAuth2Repository: Oauth2Repository
) {
    fun execute(code: String) = oAuth2Repository.googleLogin(code)
}