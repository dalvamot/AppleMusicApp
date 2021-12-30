package com.vt.applemusicapp.presenters

import com.vt.applemusicapp.model.classic.Classic
import com.vt.applemusicapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClassicPresenter(
    private val classicViewContract: IClassicView
): IClassicPresenter {
    override fun getClassicSongsFromServer() {
        let {
            Retrofit.getNetworkApi()
                .getClassicSongs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { classicSongs ->
                        classicSongs?.let {
                            classicViewContract.classicSongsUpdate(classicSongs)
                        }

                    },
                    { throwable ->
                        classicViewContract.onErrorData(throwable)
                    }
                )
        }
    }
}

interface IClassicPresenter{
    fun getClassicSongsFromServer()
}

interface IClassicView{
    fun classicSongsUpdate(classicSong: Classic)
    fun onErrorData(error: Throwable)
}