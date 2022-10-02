package com.hanyeop.songforyou.usecase.sb_recommend

import com.hanyeop.songforyou.repository.SbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecommendWithWordUseCase @Inject constructor(
    private val sbRecommendRepository: SbRecommendRepository
) {
    fun execute(listNum: Int) = sbRecommendRepository.getRecommendWithWord(listNum)
}