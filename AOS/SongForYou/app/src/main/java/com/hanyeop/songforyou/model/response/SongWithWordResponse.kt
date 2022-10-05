package com.hanyeop.songforyou.model.response

data class SongWithWordResponse(
    val info: List<WordResponse>,
    val songList: List<SongResponse>
)
