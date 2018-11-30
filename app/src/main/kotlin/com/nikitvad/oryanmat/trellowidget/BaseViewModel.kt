package com.nikitvad.oryanmat.trellowidget

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<T: Navigator> : BaseObservable(){
    val compositeDisposable = CompositeDisposable()

    open fun onBind(){}

    var navigator:T? = null

    fun unBind() {
        compositeDisposable.clear()
    }

    fun addDisposable(f: ()->Disposable){
        compositeDisposable.add(f.invoke())
    }
}