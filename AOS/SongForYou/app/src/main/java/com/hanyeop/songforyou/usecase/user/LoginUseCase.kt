package com.hanyeop.songforyou.usecase.user

import androidx.lifecycle.ViewModel
import com.hanyeop.songforyou.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    fun execute(map: HashMap<String, String>)
    = userRepository.loginUser(map)
}