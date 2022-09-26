package com.hanyeop.songforyou.usecase.user

import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class signUpUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    fun execute(token: String, userDto: UserDto)
    = userRepository.signUpUser(token, userDto)
}