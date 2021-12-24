package com.vt.applemusicapp.model.rock


import com.google.gson.annotations.SerializedName

data class RockModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val rockSongs: List<RockSong>
)