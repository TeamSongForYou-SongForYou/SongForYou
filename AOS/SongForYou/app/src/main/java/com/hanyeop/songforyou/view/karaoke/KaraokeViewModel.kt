package com.hanyeop.songforyou.view.karaoke

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.Place
import com.hanyeop.songforyou.model.response.ReviewResponse
import com.hanyeop.songforyou.usecase.kakao.GetSearchKeywordUseCase
import com.hanyeop.songforyou.usecase.review.GetReviewUseCase
import com.hanyeop.songforyou.usecase.review.UploadReviewUseCase
import com.hanyeop.songforyou.utils.ResultType
import com.hanyeop.songforyou.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class KaraokeViewModel @Inject constructor(
    private val getSearchKeywordUseCase: GetSearchKeywordUseCase,
    private val getReviewUseCase: GetReviewUseCase,
    private val uploadReviewUseCase: UploadReviewUseCase
): ViewModel() {

    private val _karaokeList: MutableStateFlow<List<Place>> = MutableStateFlow(listOf())
    val karaokeList get() = _karaokeList.asStateFlow()

    private val _reviewList: MutableStateFlow<List<ReviewResponse>> = MutableStateFlow(listOf())
    val reviewList get() = _reviewList.asStateFlow()

    private val _successMsgEvent = SingleLiveEvent<String>()
    val successMsgEvent get() = _successMsgEvent

    private val _errorMsgEvent = SingleLiveEvent<String>()
    val errorMsgEvent get() = _errorMsgEvent

    fun getSearchKeyword(query: String, x: String, y: String){
        viewModelScope.launch(Dispatchers.IO) {
            getSearchKeywordUseCase.execute(query, x, y).collectLatest {
                Log.d("test5", "getSearchKeyword: $it")
                if(it is ResultType.Success){
                    _karaokeList.value = it.data.documents
                }
            }
        }
    }

    fun getReview(name: String, address: String){
        viewModelScope.launch(Dispatchers.IO) {
            getReviewUseCase.execute(name, address).collectLatest {
                if(it is ResultType.Success){
                    _reviewList.value = it.data.data
                }
            }
        }
    }

    fun uploadReview(review: RequestBody, imgFile: MultipartBody.Part) {
        viewModelScope.launch(Dispatchers.IO) {
            uploadReviewUseCase.execute(review, imgFile)
                .collectLatest {
                    if (it is ResultType.Success) {
                        _successMsgEvent.postValue(it.data.msg)
                    } else if (it is ResultType.Fail) {
                        _errorMsgEvent.postValue(it.data.msg)
                    }
                }
        }
    }
}