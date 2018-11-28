package com.nikitvad.oryanmat.trellowidget

import android.databinding.BaseObservable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : BaseObservable(){
    val compositeDisposable = CompositeDisposable()

    fun onBind(){}

    fun unBind() {
        compositeDisposable.clear()
    }

    fun addDisposable(f: ()->Disposable){
        compositeDisposable.add(f.invoke())
    }
}