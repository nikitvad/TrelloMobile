package com.nikitvad.oryanmat.trellowidget.ui.board

import com.nikitvad.oryanmat.trellowidget.data.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BoardActivityModule {
    @Provides
    fun provideViewModel(api:Api): BoardViewModel {
        return BoardViewModel(api)
    }

}