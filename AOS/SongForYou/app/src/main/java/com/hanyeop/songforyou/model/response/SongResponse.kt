package com.hanyeop.songforyou.model.response

import com.google.gson.annotations.SerializedName

data class SongResponse (
    @SerializedName("song_seq") val SongSeq: Int,
    @SerializedName("song_title") val SongTitle: String,
    @SerializedName("song_artist_name") val SongArtistName: String,
    @SerializedName("song_youtube_view") val SongYoutubeView: Int,
    @SerializedName("song_genre") val SongGenre: Int,

)