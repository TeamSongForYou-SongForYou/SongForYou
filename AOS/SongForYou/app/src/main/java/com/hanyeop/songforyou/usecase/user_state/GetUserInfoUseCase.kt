package com.hanyeop.songforyou.usecase.user_state

import com.hanyeop.songforyou.repository.UserStateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserInfoUseCase @Inject constructor(
    private val userStateRepository: UserStateRepository
){
    fun execute() = userStateRepository.getUserInfo()
}