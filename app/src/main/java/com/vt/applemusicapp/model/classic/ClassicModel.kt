package com.vt.applemusicapp.model.classic


import com.google.gson.annotations.SerializedName

data class ClassicModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val classicSongs: List<ClassicSong>
)