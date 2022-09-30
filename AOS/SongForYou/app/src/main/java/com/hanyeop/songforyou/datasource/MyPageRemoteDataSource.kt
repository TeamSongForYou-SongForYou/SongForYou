package com.hanyeop.songforyou.datasource

import com.hanyeop.songforyou.api.MyPageApi
import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.model.response.RecordResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRemoteDataSource @Inject constructor(
    private val myPageApi: MyPageApi
) {
    fun uploadUserVoice(userSeq: Int, recordFile: MultipartBody.Part): Flow<BaseResponse<RecordResponse>> = flow {
        emit(myPageApi.uploadUserVoice(userSeq, recordFile))
    }
}