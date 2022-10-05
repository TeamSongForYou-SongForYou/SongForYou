package com.hanyeop.songforyou.usecase.ib_recommend

import com.hanyeop.songforyou.repository.IbRecommendRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIbRecommendMyRecordUseCase @Inject constructor(
    private val ibRecommendRepository: IbRecommendRepository
){
    fun execute(dateLimit: Int) = ibRecommendRepository.getIbRecommendMyRecord(dateLimit)
}