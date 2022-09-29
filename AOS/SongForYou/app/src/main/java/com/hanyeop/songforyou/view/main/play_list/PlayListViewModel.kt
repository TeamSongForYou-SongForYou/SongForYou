package com.hanyeop.songforyou.view.main.play_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanyeop.songforyou.model.response.RecordResponse
import com.hanyeop.songforyou.usecase.song_box.GetRecordListUseCase
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewModel @Inject constructor(
    private val getRecordListUseCase: GetRecordListUseCase
): ViewModel(){

    private val _recordList: MutableStateFlow<List<RecordResponse>> = MutableStateFlow(listOf())
    val recordList get() = _recordList.asStateFlow()

    fun getRecordList(){
        viewModelScope.launch(Dispatchers.IO) {
            getRecordListUseCase.execute().collectLatest {
                if(it is ResultType.Success){
                    _recordList.value = it.data.data
                }else{
                    Log.d("test5", "getRecordList: $it")
                }
            }
        }
    }
}