package com.hanyeop.songforyou.usecase.user

import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(userEmail: String)
    = userRepository.checkEmail(userEmail)
}