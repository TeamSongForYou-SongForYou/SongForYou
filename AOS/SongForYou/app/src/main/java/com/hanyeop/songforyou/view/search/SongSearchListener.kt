package com.hanyeop.songforyou.view.search

import com.hanyeop.songforyou.model.response.SongResponse

interface SongSearchListener {
    fun onItemClick(song: SongResponse)
}