package com.hanyeop.songforyou.usecase.user

import com.hanyeop.songforyou.model.dto.UserDto
import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    fun execute(userDto: UserDto)
    = userRepository.signUpUser(userDto)
}