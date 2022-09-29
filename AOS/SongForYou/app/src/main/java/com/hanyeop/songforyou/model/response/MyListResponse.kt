package com.hanyeop.songforyou.model.response

data class MyListResponse(
    val myListSeq: Int,
    val songSeq: SongResponse,
    val myListRegTime: String
)
