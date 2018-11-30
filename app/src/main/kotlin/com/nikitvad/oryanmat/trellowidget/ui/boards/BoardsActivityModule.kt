package com.nikitvad.oryanmat.trellowidget.ui.boards

import com.nikitvad.oryanmat.trellowidget.data.Api
import dagger.Module
import dagger.Provides

@Module class BoardsActivityModule{

    @Provides
    fun provideBoardsViewModel(api:Api):BoardsViewModel{
        return BoardsViewModel(api)
    }

}