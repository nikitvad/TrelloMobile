package com.nikitvad.oryanmat.trellowidget.di

import android.content.Context
import com.nikitvad.oryanmat.trellowidget.util.PreferencesUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule(){

    @Provides
    @Singleton
    fun providePreferencesUtil(context: Context):PreferencesUtil{
        return PreferencesUtil(context)
    }
}