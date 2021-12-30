package com.vt.applemusicapp.model.Rock


import com.google.gson.annotations.SerializedName

data class Rock(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val rockSongs: List<RockSong>
)