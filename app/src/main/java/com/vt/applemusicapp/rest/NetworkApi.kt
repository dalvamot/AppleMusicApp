package com.vt.applemusicapp.rest

import com.vt.applemusicapp.model.Rock.Rock
import com.vt.applemusicapp.model.classic.Classic
import com.vt.applemusicapp.model.pop.Pop
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(SEARCH)
    fun getRockSongs(
        @Query("term") type: String = ROCK,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<Rock>

    @GET(SEARCH)
    fun getPopSongs(
        @Query("term") type: String = POP,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<Pop>

    @GET(SEARCH)
    fun getClassicSongs(
        @Query("term") type: String = CLASSIC,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<Classic>

    companion object{
        private const val SEARCH = "search"
        private const val MEDIA = "music"
        private const val ENTITY = "song"
        private const val LIMIT = "100"
        private const val ROCK = "rock"
        private const val POP = "pop"
        private const val CLASSIC = "classick"

        const val BASE_URL = "https://itunes.apple.com/"
    }
}