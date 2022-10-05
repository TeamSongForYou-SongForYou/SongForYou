package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SongResponse (
    @SerializedName("songSeq") val SongSeq: Int,
    @SerializedName("songTitle") val SongTitle: String,
    @SerializedName("songArtistName") val SongArtistName: String,
    @SerializedName("songGenre") val SongGenre: String,
    @SerializedName("songYoutubeView") val SongYoutubeView: Int,
    @SerializedName("songThumbnailUrl") val songThumbnailUrl: String,
    @SerializedName("songYoutubeUrl") val songYoutubeUrl: String,
//    @SerializedName("songRegTime") val songRegTime: String,
): Serializable