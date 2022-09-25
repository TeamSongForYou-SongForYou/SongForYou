package com.hanyeop.songforyou.usecase.ub_recommend

import com.hanyeop.songforyou.repository.UbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUbRecommendMySoundUseCase @Inject constructor(
    private val ubRecommendRepository: UbRecommendRepository
){
    fun execute() = ubRecommendRepository.getUbRecommendMySound()
}