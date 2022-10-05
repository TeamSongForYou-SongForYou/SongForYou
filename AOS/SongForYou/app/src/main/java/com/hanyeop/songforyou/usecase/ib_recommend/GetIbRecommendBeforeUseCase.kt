package com.hanyeop.songforyou.usecase.ib_recommend

import com.hanyeop.songforyou.repository.IbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIbRecommendBeforeUseCase @Inject constructor(
    private val ibRecommendRepository: IbRecommendRepository
){
    fun execute(SongSeq: Int) = ibRecommendRepository.getIbRecommendBefore(SongSeq)
}