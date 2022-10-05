package com.hanyeop.songforyou.view.main.play_list.tab.record

import com.hanyeop.songforyou.model.response.RecordResponse

interface RecordListener {
    fun onItemClick(record: RecordResponse)
}