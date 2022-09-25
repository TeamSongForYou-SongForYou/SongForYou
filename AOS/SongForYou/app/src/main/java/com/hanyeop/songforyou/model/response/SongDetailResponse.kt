package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName

// TODO : Detail 에 뭐가 있는지..?
data class SongDetailResponse(
    @SerializedName("song_seq") val SongSeq: Int,
    )
