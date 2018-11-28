package com.nikitvad.oryanmat.trellowidget.di

import com.nikitvad.oryanmat.trellowidget.TrelloMobile
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ApplicationModule::class,
    PreferencesModule::class,
    RetrofitModule::class])
interface AppComponents : AndroidInjector<TrelloMobile> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): AppComponents
    }
}