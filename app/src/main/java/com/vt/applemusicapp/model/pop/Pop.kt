package com.vt.applemusicapp.model.pop


import com.google.gson.annotations.SerializedName

data class Pop(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val popSongs: List<PopSong>
)