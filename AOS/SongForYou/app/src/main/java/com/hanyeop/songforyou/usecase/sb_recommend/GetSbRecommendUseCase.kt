package com.hanyeop.songforyou.usecase.sb_recommend

import com.hanyeop.songforyou.repository.SbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSbRecommendUseCase @Inject constructor(
    private val sbRecommendRepository: SbRecommendRepository
) {
    fun execute(genre: String, age: Int, gender: String, weather: Int)
        = sbRecommendRepository.getSbRecommend(genre, age, gender, weather)
}