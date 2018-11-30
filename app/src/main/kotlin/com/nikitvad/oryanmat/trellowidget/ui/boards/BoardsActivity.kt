package com.nikitvad.oryanmat.trellowidget.ui.boards

import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.nikitvad.oryanmat.trellowidget.BaseActivity
import com.nikitvad.oryanmat.trellowidget.R
import com.nikitvad.oryanmat.trellowidget.databinding.ActivityBoardsBinding
import com.nikitvad.oryanmat.trellowidget.old.model.Board
import com.nikitvad.oryanmat.trellowidget.ui.board.BoardActivity
import javax.inject.Inject

class BoardsActivity : BaseActivity<BoardsViewModel, ActivityBoardsBinding>(), BoardsNavigator {

    @Inject
    lateinit var boardViewModel: BoardsViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_boards
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().navigator = this
    }

    override fun getViewModel(): BoardsViewModel {
        return boardViewModel
    }

    override fun openBoard(board: Board) {
        intent = Intent(this, BoardActivity::class.java)
        intent.putExtra(BoardActivity.KEY_BOARD, Gson().toJson(board))
        startActivity(intent)
    }
}
