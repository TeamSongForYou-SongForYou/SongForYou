package com.hanyeop.songforyou.usecase.user

import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(map: HashMap<String, String>)
    = userRepository.findPassword(map)
}