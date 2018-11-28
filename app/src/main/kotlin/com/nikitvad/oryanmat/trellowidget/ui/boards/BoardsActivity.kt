package com.nikitvad.oryanmat.trellowidget.ui.boards

import com.nikitvad.oryanmat.trellowidget.BaseActivity
import com.nikitvad.oryanmat.trellowidget.R
import com.nikitvad.oryanmat.trellowidget.databinding.ActivityBoardsBinding
import javax.inject.Inject

class BoardsActivity : BaseActivity<BoardsViewModel, ActivityBoardsBinding>() {

    @Inject
    lateinit var boardViewModel: BoardsViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_boards
    }

    override fun getViewModel(): BoardsViewModel {
        return boardViewModel
    }

}
