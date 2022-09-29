package com.hanyeop.songforyou.view.main.home

import com.hanyeop.songforyou.model.response.SongResponse

interface SongDetailListener {
    fun onItemClick(song: SongResponse)
}