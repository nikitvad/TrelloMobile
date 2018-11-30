package com.nikitvad.oryanmat.trellowidget.ui.boards

import com.nikitvad.oryanmat.trellowidget.Navigator
import com.nikitvad.oryanmat.trellowidget.old.model.Board

interface BoardsNavigator : Navigator{
    fun openBoard(board:Board)
}