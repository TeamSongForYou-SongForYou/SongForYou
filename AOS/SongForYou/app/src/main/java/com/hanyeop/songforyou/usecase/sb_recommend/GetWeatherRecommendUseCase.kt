package com.hanyeop.songforyou.usecase.sb_recommend

import com.hanyeop.songforyou.repository.SbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherRecommendUseCase @Inject constructor(
    private val sbRecommendRepository: SbRecommendRepository
){
    fun execute( weather: Int)
            = sbRecommendRepository.getWeatherRecommend(weather)
}