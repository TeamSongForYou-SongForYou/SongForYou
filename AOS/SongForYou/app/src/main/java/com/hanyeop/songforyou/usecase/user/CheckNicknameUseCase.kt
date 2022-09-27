package com.hanyeop.songforyou.usecase.user

import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(userNickname: String)
    = userRepository.checkNickname(userNickname)
}