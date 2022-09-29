package com.hanyeop.songforyou.repository

import com.hanyeop.songforyou.base.BaseResponse
import com.hanyeop.songforyou.datasource.SongBoxRemoteDataSource
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongBoxRepository @Inject constructor(
    private val songBoxRemoteDataSource: SongBoxRemoteDataSource
) {
    fun addSongBox(songSeq: Int): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        songBoxRemoteDataSource.addSongBox(songSeq).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun deleteSongBox(songSeq: Int): Flow<ResultType<BaseResponse<String>>> = flow {
        emit(ResultType.Loading)
        songBoxRemoteDataSource.deleteSongBox(songSeq).collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun getRecordList(): Flow<ResultType<BaseResponse<List<RecordResponse>>>> = flow {
        emit(ResultType.Loading)
        songBoxRemoteDataSource.getRecordList().collect {
            if (it.success) {
                emit(ResultType.Success(it))
            } else if (!it.success) {
                emit(ResultType.Fail(it))
            }
        }
    }.catch { e ->
        emit(ResultType.Error(e))
    }

    fun uploadRecord(songSeq: Int, recordFile: MultipartBody.Part): Flow<ResultType<BaseResponse<RecordResponse>>> = flow {
        emit(ResultType.Loading)
        songBoxRemoteDataSource.uploadRecord(songSeq, recordFile).collect {
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
