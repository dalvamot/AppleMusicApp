package com.vt.applemusicapp.rest

import com.vt.applemusicapp.model.classic.ClassicModel
import com.vt.applemusicapp.model.pop.PopModel
import com.vt.applemusicapp.model.rock.RockModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET(SEARCH)
    fun getRockSongs(
        @Query("term") type: String = ROCK,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<RockModel>

    @GET(SEARCH)
    fun getClassicSongs(
        @Query("term") type: String = CLASSIC,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<ClassicModel>

    @GET(SEARCH)
    fun getPopSongs(
        @Query("term") type: String = POP,
        @Query("amp;media") media: String = MEDIA,
        @Query("amp;entity") entity: String = ENTITY,
        @Query("amp;limit") limit: String = LIMIT
    ): Single<PopModel>

    companion object{
        private const val SEARCH = "search"
        private const val MEDIA = "music"
        private const val ENTITY = "song"
        private const val LIMIT = "50"
        private const val ROCK = "rock"
        private const val CLASSIC = "classick"
        private const val POP = "pop"
        const val BASE_URL = "https://itunes.apple.com/"
    }
}