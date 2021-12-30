package com.vt.applemusicapp.presenters

import com.vt.applemusicapp.model.pop.Pop
import com.vt.applemusicapp.rest.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopPresenter(
    private val popViewContract: IPopView
): IPopPresenter {
    override fun getPopSongsFromServer() {
        let {
            Retrofit.getNetworkApi()
                .getPopSongs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { popSongs ->
                        popSongs?.let {
                            popViewContract.popSongsUpdate(popSongs)
                        }

                    },
                    { throwable ->
                        popViewContract.onErrorData(throwable)
                    }
                )
        }
    }
}

interface IPopPresenter{
    fun getPopSongsFromServer()
}

interface IPopView{
    fun popSongsUpdate(popSong: Pop)
    fun onErrorData(error: Throwable)
}