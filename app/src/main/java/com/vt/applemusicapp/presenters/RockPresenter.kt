package com.vt.applemusicapp.presenters

import com.vt.applemusicapp.model.Rock.Rock
import com.vt.applemusicapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RockPresenter(
    private val rockViewContract: IRockView
): IRockPresenter {
    override fun getRockSongsFromServer() {

        // loved the use of the Kotlin scope functions
        let {
            Retrofit.getNetworkApi()
                .getRockSongs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { rockSongs ->
                        rockSongs?.let {
                            rockViewContract.rockSongsUpdate(rockSongs)
                        }

                    },
                    { throwable ->
                        rockViewContract.onErrorData(throwable)
                    }
                )
        }
    }
}

interface IRockPresenter{
    fun getRockSongsFromServer()
}

interface IRockView{
    fun rockSongsUpdate(rockSong: Rock)
    fun onErrorData(error: Throwable)
}