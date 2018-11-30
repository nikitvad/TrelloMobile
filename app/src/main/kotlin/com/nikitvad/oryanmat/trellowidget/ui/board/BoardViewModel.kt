package com.nikitvad.oryanmat.trellowidget.ui.board

import com.nikitvad.oryanmat.trellowidget.BaseViewModel
import com.nikitvad.oryanmat.trellowidget.Navigator
import com.nikitvad.oryanmat.trellowidget.data.Api
import com.nikitvad.oryanmat.trellowidget.old.model.BoardList
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

class BoardViewModel(val api: Api): BaseViewModel<Navigator>(){

    var lists: List<BoardList>? = null

    override fun onBind() {
        super.onBind()

        api.getCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    lists = it.body()
                }

    }
}