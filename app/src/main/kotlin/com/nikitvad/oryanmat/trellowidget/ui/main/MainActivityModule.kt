package com.nikitvad.oryanmat.trellowidget.ui.main

import com.nikitvad.oryanmat.trellowidget.old.activity.LoggedInFragment
import com.nikitvad.oryanmat.trellowidget.old.activity.LoginFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule{

    @ContributesAndroidInjector
    abstract fun loggedInFragment(): LoggedInFragment

}