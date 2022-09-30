package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.MyPageRemoteDataSource
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRepository @Inject constructor(
    private val myPageRemoteDataSource: MyPageRemoteDataSource
) {
    fun uploadUserVoice(userSeq: Int, recordFile: MultipartBody.Part): Flow<ResultType<BaseResponse<RecordResponse>>> = flow {
        emit(ResultType.Loading)
        myPageRemoteDataSource.uploadUserVoice(userSeq, recordFile).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }
}