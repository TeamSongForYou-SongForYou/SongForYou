package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName

data class SongResponse (
    @SerializedName("songSeq") val SongSeq: Int,
    @SerializedName("songTitle") val SongTitle: String,
    @SerializedName("songArtistName") val SongArtistName: String,
    @SerializedName("songGenre") val SongGenre: Int,
    @SerializedName("songYoutubeView") val SongYoutubeView: Int,
    @SerializedName("songThumbnailUrl") val songThumbnailUrl: String,
    @SerializedName("songYoutubeUrl") val songYoutubeUrl: Int,
//    @SerializedName("songRegTime") val songRegTime: String,
)