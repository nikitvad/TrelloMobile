package com.nikitvad.oryanmat.trellowidget.ui.boards

import dagger.Module
import dagger.Provides

@Module class BoardsActivityModule{

    @Provides
    fun provideBoardsViewModel():BoardsViewModel{
        return BoardsViewModel()
    }

}