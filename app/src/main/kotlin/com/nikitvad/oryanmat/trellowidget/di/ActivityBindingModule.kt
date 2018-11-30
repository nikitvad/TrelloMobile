package com.nikitvad.oryanmat.trellowidget.di

import com.nikitvad.oryanmat.trellowidget.ui.board.BoardActivity
import com.nikitvad.oryanmat.trellowidget.ui.board.BoardActivityModule
import com.nikitvad.oryanmat.trellowidget.ui.boards.BoardsActivity
import com.nikitvad.oryanmat.trellowidget.ui.boards.BoardsActivityModule
import com.nikitvad.oryanmat.trellowidget.ui.main.MainActivity
import com.nikitvad.oryanmat.trellowidget.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule{
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity():MainActivity

    @ContributesAndroidInjector(modules = [BoardsActivityModule::class])
    abstract fun boardsActivityModule():BoardsActivity

    @ContributesAndroidInjector(modules = [BoardActivityModule::class])
    abstract fun boardActivity():BoardActivity
}